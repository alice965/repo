package org.itbank.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jquery")
public class JQueryController {
	
	@RequestMapping("/qz01")
	public ModelAndView jqueryQz01() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "jquery/qz01");
		return mav;
	}
	@RequestMapping("/qz02")
	public ModelAndView jqueryQz02() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "jquery/qz02");
		return mav;
	}
	
	@RequestMapping("/01")
	public ModelAndView  jquery01() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "/jquery/01");
		return mav;
	}
	@RequestMapping("/02")
	public ModelAndView jquery02() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "jquery/02");
		return mav;
	}
	
	@RequestMapping("/03")
	public ModelAndView jquery03() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "jquery/03");
		return mav;
	}


}
