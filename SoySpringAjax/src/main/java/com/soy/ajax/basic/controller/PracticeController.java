package com.soy.ajax.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PracticeController {

	@RequestMapping(value="/ajax/practice.do", method=RequestMethod.GET) // value 경로로 get 요청이 들어오면 이를 처리하는 메서드를 지정함.
	public String showPracticePage() { // *이 메서드는 value 경로로 get요청이 왔을 때 실행됨.
		return "ajax/practice";  		// 문자열 ajax/pratcie를 반환. 이는 view 페이지의 이름을 나타내며, 클라이언트에게 해당 페이지를 보여줌.
	}
	
	// ajax 쓸 때 필수입력 (누락 주의!)
	@ResponseBody // 이후에 오는 메서드가 http응답 본문으로 데이터를 직접 반환하겠다는 것을 나타냄. 여기서는 ajax 요청을 통해 데이터를 반환할 것이므로 사용됨.
	@RequestMapping(value="/ajax/prone.do", method=RequestMethod.GET)
	public void ajaxPractice01(@RequestParam("msg") String msg) { // @어노테이션은 요청 파라미터 중 "msg"이름을 가진 값을 해당 메서드의 msg 매개변수에 매핑함.
		System.out.println("전송 받은 데이터: " + msg);
		// 메서드는 받은 msg값을 콘솔에 출력함, 따라서 전송 받은 데이터를 콘솔에 출력하는 역할을 함.
	}
	
	@ResponseBody
	@RequestMapping(value="/ajax/prtwo.do", method=RequestMethod.GET)
	public String ajaxPractice02() {
		return "Hello";
	}
	
}
