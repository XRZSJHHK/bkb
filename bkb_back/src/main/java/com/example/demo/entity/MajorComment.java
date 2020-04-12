package com.example.demo.entity;

public class MajorComment {
    private String majorId;
    private Integer majorCommentId;
    private String userName;
    private String majorCommentMessage;
    private String majorCommentTime;

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public Integer getMajorCommentId() {
        return majorCommentId;
    }

    public void setMajorCommentId(Integer majorCommentId) {
        this.majorCommentId = majorCommentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMajorCommentMessage() {
        return majorCommentMessage;
    }

    public void setMajorCommentMessage(String majorCommentMessage) {
        this.majorCommentMessage = majorCommentMessage;
    }

    public String getMajorCommentTime() {
        return majorCommentTime;
    }

    public void setMajorCommentTime(String majorCommentTime) {
        this.majorCommentTime = majorCommentTime;
    }
}
