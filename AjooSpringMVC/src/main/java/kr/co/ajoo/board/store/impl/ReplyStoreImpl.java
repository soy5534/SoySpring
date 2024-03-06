package kr.co.ajoo.board.store.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.ajoo.board.domain.ReplyVO;
import kr.co.ajoo.board.store.ReplyStore;

@Repository
public class ReplyStoreImpl implements ReplyStore{

	// 이 메서드는 MyBatis에서 제공하는 SqlSession 객체를 통해 댓글을 데이터베이스에 삽입(insert)함.
	// ReplyVO 객체에 저장된 정보를 사용하여 데이터베이스에 새로운 댓글을 추가함.
	// 성공적으로 댓글을 추가하면 데이터베이스에 영향을 미치는 행(row)의 수를 반환함.
	@Override
	public int insertReply(SqlSession session, ReplyVO replyVO) { 
		int result = session.insert("ReplyMapper.insertReply", replyVO); // *replyVO 매개변수는 데이터베이스에 삽입할 댓글 정보가 담긴 ReplyVO 객체임.
		return result; // 삽입(insert) 성공하면 1 이상의 값이, 실패하면 0이 반환됨.
		// reply클래스 자체를 안 만들고, 모든 reply를 board클래스에 작성해도 됨.
		// 이 메서드를 호출하여 새로운 댓글을 데이터베이스에 추가할 수 있으며, 호출자는 반환된 값을 통해 작업의 성공 여부를 확인할 수 있음.
	}

	@Override
	public int removeReply(SqlSession session, Integer replyNo) {
		int result = session.insert("ReplyMapper.removeReply", replyNo);		
		return result;
	}
	
	@Override
	public List<ReplyVO> selectReplyList(SqlSession session, Integer refBoardNo) { //세션을 사용하여 DB에서 댓글 목록 조회 + 조회할 게시물번호(refBoardNo)를 
																					// 파라미터로 전달하여 해당 게시물에 대한 댓글 목록을 가져옴.
		List<ReplyVO> rList = session.selectList("ReplyMapper.selectReplyList", refBoardNo); // refBoardNo 매개변수를 사용하여 조회할 게시물의 번호를 전달합니다.
																								// 조회된 댓글 목록을 List<ReplyVO> 형태로 변환함.
		return rList;
		// MyBatis 프레임워크를 사용하여 데이터베이스에서 특정 게시물에 대한 댓글 목록을 조회하는 메서드
	}

	@Override
	public int updateReply(SqlSession session, ReplyVO reply) {
		int result = session.update("ReplyMapper.updateReply", reply);
		return result;
	}


}
