package com.tukorea.itwiki.board.domain;

public class Board {

	private int pageId;
	private String title;
	private String content;
	private String pageCreate;
	private String pageUpdate;
	private String userId;
	private String category;
	private String tag;
	private int viewCount;


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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPageCreate() {
		return pageCreate;
	}

	public void setPageCreate(String pageCreate) {
		this.pageCreate = pageCreate;
	}

	public String getPageUpdate() {
		return pageUpdate;
	}

	public void setPageUpdate(String pageUpdate) {
		this.pageUpdate = pageUpdate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
