package kr.co.ajoo.board.service;

import java.util.List;

import kr.co.ajoo.board.domain.ReplyVO;

public interface ReplyService {

	/**
	 * 댓글등록 Service
	 * @param replyVO
	 * @return int
	 */
	int insertReply(ReplyVO replyVO);

	/**
	 * 댓글 목록 조회 Service
	 * @return List
	 */ 
	List<ReplyVO> selectReplyList(Integer refBoardNo);
}
