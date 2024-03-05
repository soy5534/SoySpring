package com.soy.ajax.basic.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.soy.ajax.basic.domain.UserVO;

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
	
	// 24.03.05 추가
	@ResponseBody
	@RequestMapping(value="/ajax/exthree.do", method=RequestMethod.GET)
	public String ajaxExercise03(@RequestParam("num1") Integer num1
			, @RequestParam("num2") Integer num2) {
		Integer result = num1 + num2;
//		System.out.println("num1 : " + num1 + ", num2 : " + num2);
		System.out.println("num1 + num2 : " + (num1 + num2));
//		return result+"";
		return String.valueOf(result);		
	}
	
	@ResponseBody
	@RequestMapping(value="/ajax/exfour.do"
		, produces="application/json;charset=utf-8" // 이 메서드가 생성하는 응답이 JSON 형식이며, UTF-8로 인코딩된 문자열임을 명시 (*누락주의)
		, method=RequestMethod.GET)
	public String ajaxExercise04(@RequestParam("userNum") Integer userNum) {
		List<UserVO> userList = new ArrayList<UserVO>();
		userList.add(new UserVO("khuser01", "pass01"));
		userList.add(new UserVO("khuser02", "pass02"));
		userList.add(new UserVO("khuser03", "pass03"));
		userList.add(new UserVO("khuser04", "pass04"));
		userList.add(new UserVO("khuser05", "pass05"));
		UserVO findOne = userList.get(userNum-1); // 1 입력하면 첫번째 꺼, 2 입력하면 두번째 꺼 ...
		// 서버에서는 데이터를 객체를 이용해서 다루지만
		// 화면에서는 서버에서 사용하는 객체를 쓸 수 없음
		// 서버와 화면을 연결해주는 인터페이스, JSON 필요하게 됨
		// JSONObject를 이용하여 UserVO를 JSON String으로 만들어줌
		// pom.xml ...
		// { "userId" : "khuser01", "userPw" : "pass01" }
		JSONObject json = new JSONObject();// import
		json.put("userId", findOne.getUserId());
		json.put("userPw", findOne.getUserPw());
		return json.toString(); // public String이랑 타입 맞춰줬음.		
	}
	
	@ResponseBody
	@RequestMapping(value="/ajax/exfive.do"
		, produces = "application/json;charset=utf-8" // 이 메서드가 생성하는 응답이 JSON 형식이며, UTF-8로 인코딩된 문자열임을 명시 (*누락주의)
		, method=RequestMethod.GET)
	public String ajaxExercise05(@RequestParam("findNum") Integer findNum) { // GET 요청의 파라미터 중 "findNum" 값을 Integer 타입으로 받음
		List<UserVO> userList = new ArrayList<UserVO>();
		userList.add(new UserVO("khuser01", "pass01"));
		userList.add(new UserVO("khuser02", "pass02"));
		userList.add(new UserVO("khuser03", "pass03"));
		userList.add(new UserVO("khuser04", "pass04"));
		userList.add(new UserVO("khuser05", "pass05"));
		
		// List -> JSON 배열로 바꿔주는 JSONArray가 필요
		JSONArray jsonArr = new JSONArray(); // JSONArray 생성
		
		if(findNum > 0 && findNum <= userList.size()) {
			UserVO findOne = userList.get(findNum-1);		
			JSONObject json = new JSONObject();
			json.put("userId", findOne.getUserId());
			json.put("userPw", findOne.getUserPw());	
			jsonArr.add(json); // JSONObject를 JSONArray에 추가
//			return json.toString();
		}else {
			for(UserVO user : userList) {
				JSONObject json = new JSONObject();
				json.put("userId", user.getUserId());
				json.put("userPw", user.getUserPw());
				jsonArr.add(json); // JSONObject를 JSONArray에 추가
			}
		}
		return jsonArr.toString(); // JSONArray를 문자열로 반환
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
