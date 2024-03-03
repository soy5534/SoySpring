package kr.co.ajoo.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ajoo.board.domain.BoardVO;
import kr.co.ajoo.board.domain.PageInfo;
import kr.co.ajoo.board.domain.ReplyVO;
import kr.co.ajoo.board.service.BoardService;
import kr.co.ajoo.board.service.ReplyService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bService;
	@Autowired
	private ReplyService rService;
	
	public ModelAndView showRegisterForm(ModelAndView mv) {
		
		return mv;
	}
	
	// 게시물 상세정보
	/*
	@RequestMapping(value="/board/detail.kh", method=RequestMethod.GET):
	이 어노테이션은 해당 핸들러 메서드가 "/board/detail.kh" 경로로 GET 요청을 처리함을 나타냅니다. 즉, 클라이언트로부터의 GET 요청이 해당 경로로 들어왔을 때 이 메서드가 실행됩니다.

	public String showNoticeDetail(Model model, Integer boardNo) { 
	이 부분은 핸들러 메서드의 선언부입니다. 이 메서드는 Model 객체와 게시물 번호인 boardNo를 파라미터로 받습니다. 
	*Model 객체는 컨트롤러와 뷰 사이의 데이터를 전달하는 데 사용됩니다.
	
	try { ... } catch (Exception e) { ... }: 이 부분은 예외 처리를 위한 try-catch 블록으로, 코드 실행 중에 발생하는 예외를 처리합니다. 만약 예외가 발생하면 catch 블록에서 해당 예외를 처리하게 됩니다.

	BoardVO boardVO = bService.selectOneByNo(boardNo);
	주어진 게시물 번호에 해당하는 게시물을 데이터베이스에서 조회합니다. 이를 위해 게시물 서비스(BoardService)의 selectOneByNo 메서드를 호출합니다.
	
	List<ReplyVO> rList = rService.selectReplyList(boardNo); 
	주어진 게시물 번호에 해당하는 댓글 목록을 데이터베이스에서 조회합니다. 이를 위해 댓글 서비스(ReplyService)의 selectReplyList 메서드를 호출합니다.
	
	model.addAttribute("board", boardVO); 
	조회한 게시물 객체(boardVO)를 Model에 "board"라는 이름으로 추가합니다.
	
	model.addAttribute("rList", rList); 
	조회한 댓글 목록 객체(rList)를 Model에 "rList"라는 이름으로 추가합니다.
	
	return "board/detail"; 
	조회한 게시물과 댓글 목록을 담은 Model 객체를 이용하여 "board/detail" 뷰를 반환합니다. 즉, 게시물 상세 페이지를 보여줍니다.
	
	catch (Exception e) { ... }: 
	try 블록에서 발생한 예외를 처리하는 catch 블록입니다. 예외가 발생하면 해당 예외의 메시지를 Model에 추가하고 "common/errorPage" 뷰를 반환합니다. 이는 예외가 발생했을 때 에러 페이지를 보여주는 역할을 합니다.
	
	이것이 핸들러 메서드인 showNoticeDetail의 상세한 설명입니다. 이 메서드는 클라이언트로부터의 요청을 처리하고, 게시물 상세 페이지를 보여주는 역할을 합니다.
	 */
	@RequestMapping(value="/board/detail.kh", method=RequestMethod.GET)
	public String showNoticeDetail(Model model, Integer boardNo) {	
		try {
			BoardVO boardVO = bService.selectOneByNo(boardNo);
			List<ReplyVO> rList = rService.selectReplyList(boardNo);
			model.addAttribute("board", boardVO);
			model.addAttribute("rList", rList);
			return "board/detail";
		} catch (Exception e) {			
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="/board/list.kh", method=RequestMethod.GET)
	public String showNoticeList(Model model
			, @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage) {
		try {
			Integer totalCount = 227;
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			List<BoardVO> bList = bService.selectNoticeList(pInfo);
			if(!bList.isEmpty()) {
				model.addAttribute("pInfo", pInfo);
				model.addAttribute("bList", bList);
			}else {
				// 없다고 알려줘야 함.
				// 1. 항상 에러페이지를 통해서 데이터가 없다고 했지만
				// 2. list.jsp에서 데이터가 존재하지 않습니다 메시지 출력하도록 할 수 있음
				model.addAttribute("bList", null);
			}
			return "board/list";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	
	// 페이징 처리 정보저장
	private PageInfo getPageInfo(Integer currentPage, Integer totalCount) {
		PageInfo pInfo = new PageInfo();
		int recordCountPerPage = pInfo.getRecordCountPerPage();
		int naviCountPerPage = pInfo.getNaviCountPerPage();
		int naviTotalCount;
		int startNavi;
		int endNavi;
		// Math.ceil()을 이용해서 올림하고 int 강제형변환으로 정수가 나오도록 함
		naviTotalCount = (int)Math.ceil((double)totalCount/recordCountPerPage);
		startNavi = ((int)((double)currentPage/naviCountPerPage+0.9)-1)*naviCountPerPage+1;
		endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		pInfo.setCurrentPage(currentPage);
		pInfo.setNaviTotalCount(naviTotalCount);
		pInfo.setStartNavi(startNavi);
		pInfo.setEndNavi(endNavi);
		return pInfo;
	}
	// 게시물 등록 페이지, /board/register.kh를 주소표시줄에 입력하면 register.jsp가 나타남
	@RequestMapping(value="/board/register.kh", method=RequestMethod.GET)
	public String showRegisterForm(Model model) {
		return "board/register";
	}
	// 게시물 등록
	@RequestMapping(value="/board/register.kh", method=RequestMethod.POST)
	public String insertBoard(@ModelAttribute BoardVO board
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, Model model
			, HttpServletRequest request
			, HttpSession session) {
		try {
			String writer = (String)session.getAttribute("memberId");
			if(session != null && writer != null && !"".equals(writer)) {
				board.setBoardWriter(writer);
				if(uploadFile != null && !uploadFile.isEmpty()) {
				 	Map<String, Object> bMap = this.saveFile(request, uploadFile);
				 	board.setBoardFilename((String)bMap.get("fileName"));
				 	board.setBoardFileRename((String)bMap.get("fileRename"));
				 	board.setBoardFilepath((String)bMap.get("filePath"));
				 	board.setBoardFilelength((long)bMap.get("fileLength"));
				}
			}else {
				model.addAttribute("msg", "로그인이 필요합니다.");
				return "common/errorPage";
			}
			int result = bService.insertBoard(board);
			if(result > 0) {
				return "redirect:/board/list.kh";
			}else {
				model.addAttribute("msg", "공지사항 등록이 완료되지 않았습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	private Map<String, Object> saveFile(HttpServletRequest request, MultipartFile uploadFile) throws Exception {
		String boardFolder = request.getSession().getServletContext().getRealPath("resources");
		String savePath = boardFolder + "\\buploadFiles";
		File saveFolder = new File(savePath);
		if(!saveFolder.exists()) {
			saveFolder.mkdir();		// 저장할 경로에 폴더가 없으면 폴더를 생성하도록 함.
		}
		String fileName = uploadFile.getOriginalFilename();
		// A : 1.png, B : 1.png 
		// 시간으로 파일 리네임하기
		String ext = fileName.substring(fileName.lastIndexOf(".")+1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileRename = sdf.format(new Date(System.currentTimeMillis())) + "." + ext;
		File saveFile = new File(savePath+"\\"+fileRename);
		uploadFile.transferTo(saveFile); 	// 이 코드를 통해서 파일이 저장됨.
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("fileName", fileName);
		fileMap.put("fileRename", fileRename);
		fileMap.put("filePath", "../resources/buploadFiles/"+fileRename);
		fileMap.put("fileLength", uploadFile.getSize());
		return fileMap;
	}
}
