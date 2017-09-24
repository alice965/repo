package org.itbank.app.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.itbank.app.models.MemberDaoMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/my")
public class MemberController {
	@Autowired
	MemberDaoMyBatis memberDao;
	@Autowired
	ServletContext application;
	@Autowired
	SimpleDateFormat sdf;

	@GetMapping("/profile")
	public ModelAndView profileHandle(HttpSession session) {
		ModelAndView mav = new ModelAndView("t_expr");

		String id = (String) session.getAttribute("auth_id");
		List<Map> list=memberDao.listProfile(id);
		Map pic = memberDao.latestProfile(id);
		mav.addObject("section", "my/profile");
		mav.addObject("list", list);
		mav.addObject("pic", pic);
		return mav;
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/profile")
	public ModelAndView profilePostHandle(@RequestParam(name="profile") 
			MultipartFile f, HttpSession session) throws InterruptedException {
	
		String id = (String) session.getAttribute("auth_id");
		boolean rst = false;
		String fmt = sdf.format(System.currentTimeMillis());
		String fileName = id+"_"+fmt;
		try {
			if(f.isEmpty())
				throw new Exception();
			File dst = new File(application.getRealPath("/profiles"), fileName);
			f.transferTo(dst);
			rst = !rst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rst) {
			Map data = new HashMap<>();
			data.put("id", id);
			data.put("uri", "/profiles/"+fileName);
			memberDao.addProfile(data);
		}
		
		
		ModelAndView mav = new ModelAndView("redirect:/my/profile");
		mav.addObject("rst", rst);
		
		return mav;
	}

	@GetMapping("/info")
	public String infoHandle(Model model, HttpSession session) {
		String id = (String) session.getAttribute("auth_id");
		HashMap map = memberDao.readOneById(id);
		model.addAttribute("section", "my/info");
		model.addAttribute("map", map);
		return "t_expr";
	}

	@GetMapping("/edit_info")
	public String infoGetHandle(Model model, HttpSession session) {
		String id = (String) session.getAttribute("auth_id");
		HashMap map = memberDao.readOneById(id);
		model.addAttribute("section", "my/edit_info");
		model.addAttribute("map", map);
		return "t_expr";
	}
	@PostMapping("/edit_info")
	public String infoPostHandle(@RequestParam Map param, Model model, HttpSession session) {
		String id = (String) session.getAttribute("auth_id");
		param.put("id", id);
		System.out.println("�Ķ���?"+param);
		int r = memberDao.updateInfo(param);
		//System.out.println("������Ʈ �Ͼ");
		if(r==1) {
			model.addAttribute("section", "my/edit_info");
			return "t_expr";
		}
		return "t_expr";
	}
	@GetMapping("/dropout")
	public String dropoutGetHandle(Model model, HttpSession session) {
		//String id = (String) session.getAttribute("auth_id");
		//HashMap map = memberDao.dropMember(id);
		model.addAttribute("section", "/my/dropout");
		return "t_expr";
	}
	@PostMapping("/dropout")
	public String dropoutPostHandle(@RequestParam Map param, Model model, HttpSession session) {
		String id = (String) session.getAttribute("auth_id");
		param.put("id", id);
		int cnt = session.getAttribute("limit")==null? 0: (int)(session.getAttribute("limit"));
		System.out.println("Ż��..�Ķ���?"+param);
		int r = memberDao.dropMember(param);
		if(r==1) {
			model.addAttribute("section", "/");
			session.invalidate();
			return "t_expr";
		}else {
			cnt++;	  //Ʋ�� Ƚ�� �÷��ְ�
			session.setAttribute("limit", cnt);
			if(cnt == 3) {
				session.invalidate();
			}
		}
		return "t_expr";
	}
	@RequestMapping("/list")
	public ModelAndView boardListHandle(@RequestParam(name="page", defaultValue="1") int page ) 
						throws SQLException {
		List<Map> list = memberDao.list();
		
		int psize = memberDao.countListPage();
		Map p = new HashMap();
			p.put("start", (page-1)*5+1);
			p.put("end", page*5);
		
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "my/list");
		mav.addObject("list", memberDao.listPage(p));
		mav.addObject("cnt", list.size());
		mav.addObject("size",psize/5);
		
		
		return mav;
	}

}
