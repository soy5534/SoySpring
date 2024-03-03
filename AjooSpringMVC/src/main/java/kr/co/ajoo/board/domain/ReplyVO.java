package kr.co.ajoo.board.domain;

import java.sql.Date;

public class ReplyVO {
	private int replyNo;		     // 댓글의 고유 식별자. 각 댓글은 고유한 번호를 가져야 함.
	private int refBoardNo;			 // 댓글이 참조하는 게시물의 번호를 나타냄. 즉, 이 댓글이 어떤 게시물에 달릴 것인지를 나타냄.
	private String replyContent;	 // 댓글의 내용   
	private String replyWriter;		 // 댓글의 작성자 
	private Date rCreateDate;		 // 댓글이 작성된 날짜,시간 
	private String updateYn;		 // 댓글이 수정되었는지 여부를 나타내는 변수. 일반적으로 'y''n같은 문자열 값. 이 변수를 사용하여 댓글 수정 여부를 추적할 수 있음.
	private String rStatus;			 // 댓글의 상태를 나타내는 변수. 댓글이 삭제되었는지 여부 등을 나타내는 데 사용될 수 있음.
	
	public ReplyVO() {}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getRefBoardNo() {
		return refBoardNo;
	}

	public void setRefBoardNo(int refBoardNo) {
		this.refBoardNo = refBoardNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public Date getrCreateDate() {
		return rCreateDate;
	}

	public void setrCreateDate(Date rCreateDate) {
		this.rCreateDate = rCreateDate;
	}

	public String getUpdateYn() {
		return updateYn;
	}

	public void setUpdateYn(String updateYn) {
		this.updateYn = updateYn;
	}

	public String getrStatus() {
		return rStatus;
	}

	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}

	@Override
	public String toString() {
		return "ReplyVO [replyNo=" + replyNo + ", refBoardNo=" + refBoardNo + ", replyContent=" + replyContent
				+ ", replyWriter=" + replyWriter + ", rCreateDate=" + rCreateDate + ", updateYn=" + updateYn
				+ ", rStatus=" + rStatus + "]";
	}
	
	
	
	
	
}
