package kr.co.ajoo.board.store;

import org.apache.ibatis.session.SqlSession;

import kr.co.ajoo.board.domain.ReplyVO;

public interface ReplyStorePrac {

	int insertReply(SqlSession session, ReplyVO replyVO);

}
