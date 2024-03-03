package kr.co.ajoo.board.service;

import java.util.List;

import kr.co.ajoo.board.domain.BoardVO;
import kr.co.ajoo.board.domain.PageInfo;

public interface BoardService {

	/**
	 * 게시물 등록 Service
	 * @param board
	 * @return int
	 */
	int insertBoard(BoardVO board);

	/**
	 * 게시물 목록 조회 Service
	 * @param pInfo 
	 * @return List
	 */
	List<BoardVO> selectNoticeList(PageInfo pInfo);

	/**
	 * 게시물 상세 조회 Service
	 * @param boardNo
	 * @return
	 */
	BoardVO selectOneByNo(Integer boardNo);

}
