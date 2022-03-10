package com.dc.clean.user.adapter.out.persistence.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class UserDataMapper {

    @Id
    private String name;

    private String password;

    private LocalDateTime creationTime;


    public UserDataMapper() {

    }

    public UserDataMapper(String name, String password, LocalDateTime creationTime) {
        super();
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
