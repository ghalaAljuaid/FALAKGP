package com.example.falaksign;

public class Like {

    private String userId;
    private boolean likeStatus;

    public Like(String userId, boolean likeStatus) {
        this.userId = userId;
        this.likeStatus = likeStatus;
    }

    public Like() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(boolean likeStatus) {
        this.likeStatus = likeStatus;
    }
}
