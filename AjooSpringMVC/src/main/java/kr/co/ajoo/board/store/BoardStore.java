package kr.co.ajoo.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.ajoo.board.domain.BoardVO;
import kr.co.ajoo.board.domain.PageInfo;

public interface BoardStore {

	/**
	 * 게시물 등록 Store
	 * @param session
	 * @param board
	 * @return int
	 */
	int insertBoard(SqlSession session, BoardVO board);

	/**
	 * 게시물 목록 조회 Store
	 * @param session
	 * @param pInfo 
	 * @return List
	 */
	List<BoardVO> selectNoticeList(SqlSession session, PageInfo pInfo);

	/**
	 * 게시물 상세 조회 Store
	 * @param session
	 * @param boardNo
	 * @return
	 */
	BoardVO selectOneByNo(SqlSession session, Integer boardNo);

}
