package kr.co.ajoo.board.store.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.ajoo.board.domain.ReplyVO;
import kr.co.ajoo.board.store.ReplyStore;

@Repository
public class ReplyStoreImpl implements ReplyStore{

	@Override
	public int insertReply(SqlSession session, ReplyVO replyVO) {
		int result = session.insert("ReplyMapper.insertReply", replyVO); 
		return result;
		// reply클래스 자체를 안 만들고, 모든 reply를 board클래스에 작성해도 됨.
	}

	@Override
	public List<ReplyVO> selectReplyList(SqlSession session, Integer refBoardNo) { //세션을 사용하여 DB에서 댓글 목록 조회 + 조회할 게시물번호(refBoardNo)를 
																					// 파라미터로 전달하여 해당 게시물에 대한 댓글 목록을 가져옴.
		List<ReplyVO> rList = session.selectList("ReplyMapper.selectReplyList", refBoardNo); // 댓글 목록을 List<ReplyVO> 형태로 변환함.
		return rList;
		// 이 코드는 주어진 게시물 번호에 대한 댓글 목록을 DB에서 조회하여 반환하는 기능을 구현하고 있음.
	}

}
