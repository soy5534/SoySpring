package kr.co.ajoo.board.store.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.ajoo.board.domain.BoardVO;
import kr.co.ajoo.board.domain.PageInfo;
import kr.co.ajoo.board.store.BoardStore;

@Repository
public class BoardStoreImpl implements BoardStore{
//0221 추가
	@Override
	public int insertBoard(SqlSession session, BoardVO board) {
		int result = session.insert("BoardMapper.insertBoard", board);
		return result;
	}

	@Override
	public List<BoardVO> selectNoticeList(SqlSession session, PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<BoardVO> bList = session.selectList("BoardMapper.selectNoticeList", null, rowBounds);
		return bList;
	}

	@Override
	public BoardVO selectOneByNo(SqlSession session, Integer boardNo) {
		BoardVO boardVO = session.selectOne("BoardMapper.selectOneByNo", boardNo);
		return boardVO;
	}

}
















