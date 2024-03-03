package kr.co.ajoo.board.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ajoo.board.domain.ReplyVO;
import kr.co.ajoo.board.service.ReplyServicePrac;
import kr.co.ajoo.board.store.ReplyStorePrac;

@Service
public class ReplyServicePracImpl implements ReplyServicePrac{

@Autowired
private SqlSession session;
@Autowired
private ReplyStorePrac rStore;

	@Override
	public int insertReply(ReplyVO replyVO) {
		int result = rStore.insertReply(session, replyVO);
		return result;   
	}
	
	

}
