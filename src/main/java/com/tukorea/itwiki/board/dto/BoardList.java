package com.tukorea.itwiki.board.dto;

public class BoardList {

	private int no; // 게시판 번호
	private int boardSeq; // 게시판 시퀀스
	private String title; // 제목
	private String writer; // 작성자
	private int hits; // 조회수
	private String regDt; // 등록일시

	public int getBoardSeq() {
		return boardSeq;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
}
