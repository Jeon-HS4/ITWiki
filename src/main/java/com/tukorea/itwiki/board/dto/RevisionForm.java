package com.tukorea.itwiki.board.dto;

import java.util.Date;

public class RevisionForm {

	private int pageId; // 페이지 아이디
	private String userId; // 유저 아이디
	private String content; // 내용

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
