package kr.co.ajoo.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ajoo.board.domain.ReplyVO;
import kr.co.ajoo.board.service.ReplyService;
import kr.co.ajoo.board.store.ReplyStore;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyStore rStore;
	@Autowired
	private SqlSession session; // myBatis(SQL 매핑 프레임워크)에서 사용되는 세션 객체. 데이터베이스와의 연결을 관리함.
	
	@Override
	public int insertReply(ReplyVO replyVO) {
		int result = rStore.insertReply(session, replyVO);
		return result;
	}

	@Override
	public List<ReplyVO> selectReplyList(Integer refBoardNo) {
		List<ReplyVO> rList = rStore.selectReplyList(session, refBoardNo);
		return rList;
	}

}
