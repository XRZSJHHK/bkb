package com.example.demo.entity;

public class SchoolComment {
    private String schoolId;
    private Integer SchoolCommentId;
    private String userName;
    private String schoolCommentMessage;
    private String SchoolCommentTime;

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getSchoolCommentId() {
        return SchoolCommentId;
    }

    public void setSchoolCommentId(Integer schoolCommentId) {
        SchoolCommentId = schoolCommentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSchoolCommentMessage() {
        return schoolCommentMessage;
    }

    public void setSchoolCommentMessage(String schoolCommentMessage) {
        this.schoolCommentMessage = schoolCommentMessage;
    }

    public String getSchoolCommentTime() {
        return SchoolCommentTime;
    }

    public void setSchoolCommentTime(String schoolCommentTime) {
        SchoolCommentTime = schoolCommentTime;
    }
}
