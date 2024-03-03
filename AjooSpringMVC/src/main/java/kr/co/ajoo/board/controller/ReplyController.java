package kr.co.ajoo.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.ajoo.board.domain.ReplyVO;
import kr.co.ajoo.board.service.ReplyService;

@Controller
public class ReplyController {

@Autowired
private ReplyService rService;
	
	@RequestMapping(value="/reply/add.kh", method=RequestMethod.POST)
	public String insertReply(Model model
			, HttpSession session
			, @ModelAttribute ReplyVO replyVO) { // Model, HttpSession 및 ReplyVO 객체를 매개변수로 받음. 이 메서드는 댓글을 추가하는 기능을 담당.
		try {
			String memberId = (String) session.getAttribute("memberId"); // 세션에서 "memberId"라는 이름의 속성을 가져와서 해당 속성이 가진 값을 문자열로 memberId 변수에 저장
			if(memberId != null) { 				  // 로그인 한 경우
				replyVO.setReplyWriter(memberId); // replyVO 객체의 replyWriter 필드를 해당 memberId로 설정합니다.
			}else {
				model.addAttribute("msg", "로그인이 필요합니다.");
				return "common/errorPage";
			}
			int result = rService.insertReply(replyVO); // 댓글 서비스(rService)를 통해 replyVO 객체에 담긴 댓글 정보를 DB에 삽입하고, 그 결과를 result 변수에 저장함.
			return "redirect:/board/detail.kh?boardNo="+replyVO.getRefBoardNo(); // 댓글이 성공적으로 삽입되면, 삽입된 댓글이 속한 게시글의 상세 페이지로 리다이렉트
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage()); // try 블록에서 예외가 발생하면, 발생한 예외의 메시지를 에러 페이지로 전달합
			return "common/errorPage";
		}
	}
	
}
