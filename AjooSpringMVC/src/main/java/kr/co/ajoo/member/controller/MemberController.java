package kr.co.ajoo.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.ajoo.member.domain.MemberVO;
import kr.co.ajoo.member.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService mService;
	
	@RequestMapping(value="/member/register.kh", method=RequestMethod.GET)
	public String showInsertForm() {
		return "member/insert";
	}
	
	@RequestMapping(value="/member/register.kh", method=RequestMethod.POST)
	public String insertMember(
			@ModelAttribute MemberVO member
//			@RequestParam("memberId") String memberId
//			, @RequestParam("memberPw") String memberPw
//			, @RequestParam("memberName") String memberName
//			, @RequestParam("memberAge") int memberAge
//			, @RequestParam("memberGender") String memberGender
//			, @RequestParam("memberEmail") String memberEmail
//			, @RequestParam("memberPhone") String memberPhone
//			, @RequestParam("memberAddress") String memberAddress
//			, @RequestParam("memberHobby") String memberHobby
			, Model model) {
		try {
//			MemberVO member = new MemberVO(memberId, memberPw, memberName, memberAge, memberGender
//					, memberEmail, memberPhone, memberAddress, memberHobby);
			int result = mService.insertMember(member);
			if(result > 0) {
				// 로그인 페이지
				return "redirect:/index.jsp";
			}else {
				// 실패하면 에러페이지
				// request.setAttribute("msg", "Service Failed");
				// request.getRequestDispatcher("").forward(request, response);
				model.addAttribute("msg", "Service Failed!");
				return "common/errorPage";
			}
		} catch (Exception e) {
			// TODO: handle exception
			// 예외발생시 에러페이지
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	
	// 수정 페이지로 이동
	@RequestMapping(value="/member/update.kh", method=RequestMethod.GET)
	public String showModifyForm(HttpSession session, Model model) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			MemberVO member = null;
			if(memberId != null) {
				member = mService.getOneById(memberId);
			}
			if(member != null) {
				model.addAttribute("member", member);
				return "member/modify";
			}else {
				model.addAttribute("msg", "회원 정보 조회를 완료하지 못하였습니다.");
				return "common/errorPage";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}

	// 회원 정보 수정
	@RequestMapping(value="/member/update.kh", method=RequestMethod.POST)
	public String modifyMember(
			@ModelAttribute MemberVO member
//			String memberId
//			, @RequestParam("memberPw") String memberPw
//			, String memberEmail
//			, String memberPhone
//			, String memberAddress
//			, String memberHobby
			, Model model) {
		try {
			// UPDATE MEMBER_TBL SET MEMBER_PW = ? WHERE MEMBER_ID = ?
//			MemberVO member = new MemberVO(memberId, memberPw, memberEmail
//						, memberPhone, memberAddress, memberHobby);
			int result = mService.updateMember(member);
			if(result > 0) {
				// success -> 마이페이지 이동
				return "redirect:/member/mypage.kh";
			}else {
				// fail -> 에러페이지 이동
				model.addAttribute("msg", "회원 정보 수정을 완료하지 못하였습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	@RequestMapping(value="/member/delete.kh", method=RequestMethod.GET)
	public String deleteMember(String memberId
			, HttpSession session
			, Model model) {
		try {
			String sessionId = (String)session.getAttribute("memberId");
			int result = 0;
			// 세션에 있는 아이디와 지우려고 하는 아이디가 같은 확인
			if(sessionId != null && sessionId.equals(memberId)) {
				result = mService.deleteMember(memberId);
			}else {
				model.addAttribute("msg", "로그인을 해야 합니다.");
				return "common/errorPage";
			}
			// 회원탈퇴가 성공했는지 확인
			if(result > 0) {
				return "redirect:/member/logout.kh";
			}else {
				model.addAttribute("msg", "회원 탈퇴가 완료되지 않았습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}

	@RequestMapping(value="/member/login.kh", method=RequestMethod.POST)
	public String memberLogin(
			  String memberId
			, @RequestParam("memberPw") String memberPw
			, Model model
			, HttpSession session) {
		try {
			MemberVO member = new MemberVO();
			member.setMemberId(memberId);
			member.setMemberPw(memberPw);
			member = mService.checkMemberLogin(member);
			if(member != null) {
				// 로그인 성공!, Session에 저장
				session.setAttribute("memberId", member.getMemberId());
				session.setAttribute("memberName", member.getMemberName());
				return "redirect:/index.jsp";
			}else {
				// 로그인 실패, No Data Found!
				model.addAttribute("msg", "No Data Found!!");
				return "common/errorPage";
			}
		} catch (Exception e) {
			// TODO: handle exception
			// 그 외의 오류 발생(쿼리문 오타, NullPointerExceptino 등..)
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="/member/logout.kh", method=RequestMethod.GET)
	public String memberLogout(HttpSession session, Model model) {
		try {
			if(session != null) {
				session.invalidate();
				return "redirect:/index.jsp";
			}else {
				model.addAttribute("msg", "로그아웃을 완료하지 못하였습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	// 마이페이지 Controller
	@RequestMapping(value="/member/mypage.kh", method=RequestMethod.GET)
	public String showMyPage(HttpSession session, Model model) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			MemberVO member = null;
			if(memberId != null) {
				member = mService.getOneById(memberId);
			}
			if(member != null) {
				model.addAttribute("member", member);
				return "member/mypage";
			}else {
				model.addAttribute("msg", "회원 정보 조회를 완료하지 못하였습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
}







































