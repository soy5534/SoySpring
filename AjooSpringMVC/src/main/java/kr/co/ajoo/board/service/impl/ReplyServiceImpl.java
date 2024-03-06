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
	
	// 댓글 추가 메소드. ReplyStore의 insertReply 메소드를 호출하여 댓글을 데이터베이스에 추가하고 결과를 반환함.
	@Override
	public int insertReply(ReplyVO replyVO) {
		int result = rStore.insertReply(session, replyVO);
		return result;
	}

	// 특정 게시물에 대한 댓글 목록을 조회하는 메소드. ReplyStore의 selectReplyList 메소드를 호출하여 해당 게시물에 대한 댓글 목록을 가져옴.
	@Override
	public List<ReplyVO> selectReplyList(Integer refBoardNo) {
		List<ReplyVO> rList = rStore.selectReplyList(session, refBoardNo);
		return rList;
	}

	@Override
	public int removeReply(Integer replyNo) {
		int result = rStore.removeReply(session, replyNo);
		return result;
	}

	@Override
	public int updateReply(ReplyVO reply) {
		int result = rStore.updateReply(session, reply);
		return result;
	}

}
