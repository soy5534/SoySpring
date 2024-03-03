package kr.co.ajoo.board.store.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.ajoo.board.domain.ReplyVO;
import kr.co.ajoo.board.store.ReplyStorePrac;

@Repository
public class ReplyStorePracImpl implements ReplyStorePrac{

	@Override
	public int insertReply(SqlSession session, ReplyVO replyVO) {
		int result = session.insert("ReplyMapper.insertReply", replyVO);
		return result;
	}

}
