package com.pwc.assignment.model.authentication;

import com.pwc.assignment.model.SystemUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AuthenticationUser extends User {
    private SystemUser systemUser;

    public AuthenticationUser(String username, String password, Collection<? extends GrantedAuthority> authorities, SystemUser systemUser) {
        super(username, password, authorities);
        this.systemUser = systemUser;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }
}
