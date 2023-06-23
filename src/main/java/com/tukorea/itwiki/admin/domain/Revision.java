package com.tukorea.itwiki.admin.domain;

public class Revision {
    private int revisionId;
    private String pageUpdate;
    private String content;
    private int pageId;

    public int getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(int revisionId) {
        this.revisionId = revisionId;
    }

    public String getPageUpdate() {
        return pageUpdate;
    }

    public void setPageUpdate(String pageUpdate) {
        this.pageUpdate = pageUpdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
}
