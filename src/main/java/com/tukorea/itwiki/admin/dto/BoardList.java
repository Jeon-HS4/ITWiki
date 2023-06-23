package com.tukorea.itwiki.board.dto;

public class BoardList {

	private int boardNo; // 게시판 번호
	private int pageId;
	private String title;
	private String pageUpdate;
	private String category;
	private String tag;
	private int viewCount;


	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPageUpdate() {
		return pageUpdate;
	}

	public void setPageUpdate(String pageUpdate) {
		this.pageUpdate = pageUpdate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
}
