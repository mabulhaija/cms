package com.pwc.assignment.model;

import java.time.LocalDateTime;

public class ProjectUsers {

    private int projectId;
    private int userId;
    private String projectName;
    private String userName;
    private int addedBy;
    private LocalDateTime addedDate;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(int addedBy) {
        this.addedBy = addedBy;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    public String toString() {
        return "ProjectUsers{" +
                "projectId=" + projectId +
                ", userId=" + userId +
                ", projectName='" + projectName + '\'' +
                ", userName='" + userName + '\'' +
                ", addedBy=" + addedBy +
                ", addedDate=" + addedDate +
                '}';
    }
}
