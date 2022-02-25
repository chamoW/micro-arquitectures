package com.dc.clean.user.entity.dto;

public class UserDto {

    private String name;
    private String password;


    public boolean passwordIsValid() {
        return password != null && password.length() > 5;
    }


    public UserDto() {
    }

    public UserDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
