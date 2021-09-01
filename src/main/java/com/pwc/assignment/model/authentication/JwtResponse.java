package com.pwc.assignment.model.authentication;

import com.pwc.assignment.model.SystemUser;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private final String jwttoken;
    private final String username;

    private SystemUser systemUser;

    public JwtResponse(String jwttoken, String username, SystemUser systemUser) {
        this.jwttoken = jwttoken;
        this.username = username;
        this.systemUser = systemUser;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public String getUsername() {
        return username;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }
}
