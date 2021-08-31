package com.pwc.assignment.dao;

import com.pwc.assignment.dao.mapper.DepartmentMapper;
import com.pwc.assignment.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class DepartmentDao implements DaoOperations<Department> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Department> getEntities(int size, int offset) {
        return jdbcTemplate.query("select id, name, description,  added_by, added_date from departments "
                        + " order by id desc limit ? offset ?;",
                new DepartmentMapper(), size, offset);
    }

    @Override
    public List<Department> getEntities(int size, int offset, String name) {
        return jdbcTemplate.query("select id, name, description,  added_by, added_date from departments "
                        + " where name ilike concat('%',?,'%') order by id desc limit ? offset ?;",
                new DepartmentMapper(), size, offset);
    }

    @Override
    public boolean isPresent(Integer id) {
        Integer result = jdbcTemplate.queryForObject("select count(*) from departments where id = ? ;", Integer.class, id);
        return result != null && result > 0;
    }

    @Override
    public Department getById(Integer id) {
        return jdbcTemplate.queryForObject("select id, name, description, added_by, added_date from departments "
                        + " where id = ?;",
                new DepartmentMapper(), id);
    }

    @Override
    public Department insertEntity(Department department) {

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement("insert into departments (name , description , added_by) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, department.getName());
            statement.setString(2, department.getDescription());
            statement.setInt(3, department.getAddedBy());
            return statement;
        }, holder);

        department.setId(Integer.valueOf(holder.getKeys().get("id").toString()));
        department.setAddedDate(Timestamp.valueOf(holder.getKeys().get("added_date").toString()).toLocalDateTime());

        return department;
    }

    @Override
    public void updateEntity(Department department) {
        jdbcTemplate.update("update departments set  name = ? , description = ?  WHERE id = ? ",
                department.getName(), department.getDescription(), department.getId());
    }

    @Override
    public void deleteEntity(Integer id) {
        jdbcTemplate.update("delete from departments where id = ? ", id);
    }
}
