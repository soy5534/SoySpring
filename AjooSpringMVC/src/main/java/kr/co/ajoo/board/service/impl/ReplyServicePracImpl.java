package kr.co.ajoo.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ajoo.board.domain.ReplyVO;
import kr.co.ajoo.board.service.ReplyServicePrac;
import kr.co.ajoo.board.store.ReplyStore;
import kr.co.ajoo.board.store.ReplyStorePrac;

@Service
public class ReplyServicePracImpl implements ReplyServicePrac{

	@Autowired
	private ReplyStore rStore;
	@Autowired
	private SqlSession session;
	@Override
	
	public int insertReply(ReplyVO replyVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	

}
