package com.dc.clean.user.usercase.dto;

public class UserResponseModel {
    private String login;
    private String creationTime;

    public UserResponseModel(String login, String creationTime) {
        this.login = login;
        this.creationTime = creationTime;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
