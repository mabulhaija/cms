package com.pwc.assignment.dao.mapper;

import com.pwc.assignment.model.Role;
import com.pwc.assignment.model.SystemUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<SystemUser> {
    @Override
    public SystemUser mapRow(ResultSet resultSet, int i) throws SQLException {
        SystemUser systemUser = new SystemUser();
        systemUser.setId(resultSet.getInt("id"));
        systemUser.setUserName(resultSet.getString("user_name"));
        systemUser.setDepartmentId(resultSet.getInt("department_id"));
        systemUser.setDepartmentName(resultSet.getString("department_name"));
        systemUser.setName(resultSet.getString("name"));
        systemUser.setEmail(resultSet.getString("email"));
        systemUser.setRole(Role.getRole(resultSet.getString("role")));
        systemUser.setAddedBy(resultSet.getInt("added_by"));
        systemUser.setAddedDate(resultSet.getTimestamp("added_date").toLocalDateTime());

        return systemUser;
    }
}
