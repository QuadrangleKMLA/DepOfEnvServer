package com.quadrangle.depOfEnv.payload.response;

import java.util.List;

public class UserInfoResponse {
    private int id;
    private String username;
    private List<String> roles;

    public UserInfoResponse(int id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
