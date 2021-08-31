package com.pwc.assignment.dao.mapper;

import com.pwc.assignment.model.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper implements RowMapper<Project> {
    @Override
    public Project mapRow(ResultSet resultSet, int i) throws SQLException {
        Project project = new Project();

        project.setId(resultSet.getInt("id"));
        project.setName(resultSet.getString("name"));
        project.setDescription(resultSet.getString("description"));
        project.setAddedBy(resultSet.getInt("added_by"));
        project.setAddedDate(resultSet.getTimestamp("added_date").toLocalDateTime());

        return project;
    }
}
