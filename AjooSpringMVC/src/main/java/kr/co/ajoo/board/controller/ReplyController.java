package kr.co.ajoo.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ajoo.board.domain.ReplyVO;
import kr.co.ajoo.board.service.ReplyService;

@Controller //("ReplyControllerPrac")
public class ReplyController {

@Autowired
private ReplyService rService;

	// prac //	

	// 댓글등록 + ajax
	@ResponseBody // 해당 메소드가 HTTP 응답의 본문(body)에 직접 데이터를 작성하여 클라이언트로 반환된다는 것을 나타냄.
	@RequestMapping(value="/reply/add.kh", method=RequestMethod.POST) // 해당 메소드가 "/reply/add.kh" 경로로 POST 요청을 처리한다는 것을 나타냄.
	public String insertReplyAjax(@ModelAttribute ReplyVO reply 	  // ReplyVO 객체를 받아서 DB에 댓글을 추가하고 결과를 문자열로 반환함.
			, HttpSession session) { 								  // HttpSession: 세션 객체를 받아오는 매개변수
		try {
			String replyWriter = (String)session.getAttribute("memberId"); // 세션에서 "memberId"라는 이름의 속성을 가져와서 replyWriter 변수에 저장
			int result = 0; // 댓글 추가 결과를 나타내는 변수를 초기화 함
			if(replyWriter != null && !replyWriter.equals("")) { // *replyWriter가 null이 아니고 빈 문자열이 아니라면(*즉 로그인 상태) 아래 코드를 실행함
				reply.setReplyWriter(replyWriter); // ReplyVO 객체(reply)의 작성자를 세션에서 가져온 사용자 아이디(replyWriter)로 설정함.
				result = rService.insertReply(reply); // 댓글 서비스(rService)를 사용하여 댓글을 DB에 추가하고 그 결과를 result 변수에 저장함.
			}else {
				return "Login needed"; // Login neened 문자열 반환
			}
			if(result > 0) { // 댓글 추가 성공여부 확인
				return "success"; // 댓글 추가 성공
			}else {
				return "fail"; // 댓글 추가 실패
			}
		} catch (Exception e) {
			return e.getMessage(); // 예외가 발생하면 해당 예외 메세지를 반환함.
		}		
	}


//	@RequestMapping(value="/reply/add.kh", method=RequestMethod.POST)
//	public String insertReply(Model model
//			, HttpSession session
//			, @ModelAttribute ReplyVO replyVO) { // Model, HttpSession 및 ReplyVO 객체를 매개변수로 받음. 이 메서드는 댓글을 추가하는 기능을 담당.
//		try {
//			String memberId = (String) session.getAttribute("memberId"); // 세션에서 "memberId"라는 이름의 속성을 가져와서 해당 속성이 가진 값을 문자열로 memberId 변수에 저장
//			if(memberId != null) { 				  // 로그인 한 경우
//				replyVO.setReplyWriter(memberId); // replyVO 객체의 replyWriter 필드를 해당 memberId로 설정합니다.
//			}else {
//				model.addAttribute("msg", "로그인이 필요합니다.");
//				return "common/errorPage";
//			}
//			int result = rService.insertReply(replyVO); // 댓글 서비스(rService)를 통해 replyVO 객체에 담긴 댓글 정보를 DB에 삽입하고, 그 결과를 result 변수에 저장함.
//			return "redirect:/board/detail.kh?boardNo="+replyVO.getRefBoardNo(); // 댓글이 성공적으로 삽입되면, 삽입된 댓글이 속한 게시글의 상세 페이지로 리다이렉트
//		} catch (Exception e) {
//			model.addAttribute("msg", e.getMessage()); // try 블록에서 예외가 발생하면, 발생한 예외의 메시지를 에러 페이지로 전달합
//			return "common/errorPage";
//		}
//	}
	
}
