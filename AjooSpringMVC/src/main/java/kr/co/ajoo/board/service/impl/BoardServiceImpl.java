package kr.co.ajoo.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ajoo.board.domain.BoardVO;
import kr.co.ajoo.board.domain.PageInfo;
import kr.co.ajoo.board.service.BoardService;
import kr.co.ajoo.board.store.BoardStore;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardStore bStore;
	@Autowired
	private SqlSession session;
	
	@Override
	public int insertBoard(BoardVO board) {
		int result = bStore.insertBoard(session, board);
		return result;
	}

	@Override
	public List<BoardVO> selectNoticeList(PageInfo pInfo) {
		List<BoardVO> bList = bStore.selectNoticeList(session, pInfo);
		return bList;
	}

	@Override
	public BoardVO selectOneByNo(Integer boardNo) {
		BoardVO boardVO = bStore.selectOneByNo(session, boardNo);
		return boardVO;
	}

}
