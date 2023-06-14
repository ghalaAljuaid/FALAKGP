package com.example.falaksign;

public class Comment {

    private String userId;
    private String comment;

    public Comment(String userId, String comment) {
        this.userId = userId;
        this.comment = comment;
    }

    public Comment() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
