package com.pwc.assignment.model;

import org.apache.logging.log4j.util.Strings;

public enum Role {
    MANAGER, EMPLOYEE;

    public static Role getRole(String name) {
        if (Strings.isBlank(name)) {
            return null;
        }
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(name)) {
                return role;
            }
        }
        return null;
    }
}
