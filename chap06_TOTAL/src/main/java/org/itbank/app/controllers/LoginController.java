package org.itbank.app.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.itbank.app.models.MemberDaoMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class LoginController {
	@Autowired
	MemberDaoMyBatis memberDao;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	JavaMailSender sender;
	
	@GetMapping("/login")
	public String loginHandle(Model model, HttpSession session, 
							  HttpServletResponse resp, 
							  @CookieValue(name = "auth_id",required=false) String val) throws JsonParseException, JsonMappingException, IOException {
		
		model.addAttribute("section", "login");
		if(val != null) {	// 로그인 인증 쿠키가 있을 때
			model.addAttribute("section", "index");
			session.setAttribute("auth_id", val);
			System.out.println("val : " + val);
		}else {
			model.addAttribute("section", "login");
		}
		return "t_expr";
	}
	
	@PostMapping("/login")
	public ModelAndView sessionHandle(@RequestParam Map param, @RequestBody String body,HttpServletResponse resp, HttpSession session) throws SQLException {
		ModelAndView mav = new ModelAndView();
		int t = memberDao.existOne(param);
		if (t == 1) {	//일치하는 회원이 있다면 
			HashMap u = memberDao.readOneByIdOrEmail((String)param.get("idmail"));
				//세션에 올림
			session.setAttribute("auth", u); 
			session.setAttribute("auth_id", u.get("ID"));
				//자동로그인 체크하고 넘겼다면 쿠키 생성
			//쿠키생성
			Cookie logChk=new Cookie("auth_id", (String) u.get("ID"));
			logChk.setMaxAge(60*60*24*7);
			logChk.setPath("/");
			resp.addCookie(logChk);
			
			mav.setViewName("redirect:/");
		} else {
			mav.setViewName("t_expr");
			mav.addObject("section", "login");
			mav.addObject("temp", "temp");
			/*
			mav.setViewName("redirewct:/login");
			mav.addObject("mode", "f");
			*/
		}
		return mav;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logoutHandle(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		session.invalidate();
		
		Cookie[] ar= request.getCookies();
		Map<String, String> ckmap = new HashMap<>();
		if(ar != null) {
			 for(Cookie c:ar) {
				 ckmap.put(c.getName(), c.getValue());
			 }
			 
			 if(ckmap.containsKey("auth_id")) {
				Cookie c = new Cookie("auth_id","1");
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
				
			 }
		}else {
			 System.out.println("쿠키삭제안됨....");
		}
		
		ModelAndView mav = new ModelAndView("redirect:/");
		//mav.addObject("rst", rst);
		return mav;
	}
}





