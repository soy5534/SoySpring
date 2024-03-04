package kr.co.ajoo.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.ajoo.board.domain.ReplyVO;
import kr.co.ajoo.board.service.ReplyServicePrac;

@Controller
public class ReplyControllerPrac {

@Autowired
private ReplyServicePrac rService;

	@RequestMapping(value="/reply/add.kh", method=RequestMethod.POST)
	public String insertReply(Model model, HttpSession session, @ModelAttribute ReplyVO replyVO) {
		try {
			String memberId = (String) session.getAttribute("memberId");
			if(memberId != null) {
				replyVO.setReplyWriter(memberId);
			}else {
				model.addAttribute("msg", "로그인이 필요합니다.");
				return "common/errorPage";
			}
			int result = rService.insertReply(replyVO);
			return "redirect:/board/detail.kh?boardNo=" + replyVO.getRefBoardNo();
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}

	
	
	
}
