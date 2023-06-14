package com.example.falaksign;

public class PostItem {

    private String image;
    private String comment;
    private int likeNumber;
    private int commentNumber;
    private String id;
    private String postID;

    public PostItem(String image, String comment, int likeNumber, int commentNumber, String id, String postID) {
        this.image = image;
        this.comment = comment;
        this.likeNumber = likeNumber;
        this.commentNumber = commentNumber;
        this.id = id;
        this.postID = postID;
    }

    public PostItem() {
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
