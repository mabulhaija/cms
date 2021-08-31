package com.pwc.assignment.dao.mapper;

import com.pwc.assignment.model.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        Department department = new Department();

        department.setId(resultSet.getInt("id"));
        department.setName(resultSet.getString("name"));
        department.setDescription(resultSet.getString("description"));
        department.setAddedBy(resultSet.getInt("added_by"));
        department.setAddedDate(resultSet.getTimestamp("added_date").toLocalDateTime());

        return department;
    }
}
