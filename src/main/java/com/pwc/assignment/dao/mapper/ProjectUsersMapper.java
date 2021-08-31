package com.pwc.assignment.dao.mapper;

import com.pwc.assignment.model.ProjectUsers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectUsersMapper implements RowMapper<ProjectUsers> {
    @Override
    public ProjectUsers mapRow(ResultSet resultSet, int i) throws SQLException {
        ProjectUsers projectUsers = new ProjectUsers();

        projectUsers.setProjectId(resultSet.getInt("project_id"));
        projectUsers.setUserId(resultSet.getInt("user_id"));
        projectUsers.setProjectName(resultSet.getString("project_name"));
        projectUsers.setUserName(resultSet.getString("user_name"));
        projectUsers.setAddedBy(resultSet.getInt("added_by"));
        projectUsers.setAddedDate(resultSet.getTimestamp("added_date").toLocalDateTime());

        return projectUsers;
    }
}
