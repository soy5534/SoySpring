package kr.co.ajoo.board.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class BoardVO {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String boardFilename;
	private String boardFileRename;
	private String boardFilepath;
	private long boardFilelength;
	private int boardCount;
	private Date bCreateDate;
	private Timestamp bUpdateDate;
	private char bStatus;
	
	public BoardVO() {}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardFilename() {
		return boardFilename;
	}

	public void setBoardFilename(String boardFilename) {
		this.boardFilename = boardFilename;
	}

	public String getBoardFileRename() {
		return boardFileRename;
	}

	public void setBoardFileRename(String boardFileRename) {
		this.boardFileRename = boardFileRename;
	}

	public String getBoardFilepath() {
		return boardFilepath;
	}

	public void setBoardFilepath(String boardFilepath) {
		this.boardFilepath = boardFilepath;
	}

	public long getBoardFilelength() {
		return boardFilelength;
	}

	public void setBoardFilelength(long boardFilelength) {
		this.boardFilelength = boardFilelength;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public Date getbCreateDate() {
		return bCreateDate;
	}

	public void setbCreateDate(Date bCreateDate) {
		this.bCreateDate = bCreateDate;
	}

	public Timestamp getbUpdateDate() {
		return bUpdateDate;
	}

	public void setbUpdateDate(Timestamp bUpdateDate) {
		this.bUpdateDate = bUpdateDate;
	}

	public char getbStatus() {
		return bStatus;
	}

	public void setbStatus(char bStatus) {
		this.bStatus = bStatus;
	}

	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardWriter=" + boardWriter + ", boardFilename=" + boardFilename + ", boardFileRename="
				+ boardFileRename + ", boardFilepath=" + boardFilepath + ", boardFilelength=" + boardFilelength
				+ ", boardCount=" + boardCount + ", bCreateDate=" + bCreateDate + ", bUpdateDate=" + bUpdateDate
				+ ", bStatus=" + bStatus + "]";
	}
	
	
}
