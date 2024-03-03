package com.soy.ajax.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AjooAjaxController {

	@RequestMapping(value="/ajax/example.do", method=RequestMethod.GET)
	public String showExamplePage() {
		return "ajax/example";
	}

	@ResponseBody // ajax 쓸 때 필수입력 (누락주의!)
	@RequestMapping(value="/ajax/exone.do", method=RequestMethod.GET)
	public void ajaxExercise01(@RequestParam("msg") String msg) {
		System.out.println("전송 받은 데이터 : " + msg) ;
	}
	
	@ResponseBody // ajax 쓸 때 필수입력 (누락주의!)
	@RequestMapping(value="/ajax/extwo.do", method=RequestMethod.GET)
	public String ajaxExercise02() {
		return "Hello";
	}
	
	// Ajax + ModelAndView = X
//	public ModelAndView ajaxex01(ModelAndView mv) {
//		return mv;
//	}
	
	// Ajax + Model = X
//	public String ajaxex02(Model model) {
//		model.addAttribute("msg", "good");
//		return "";
//	}
	
	
}
