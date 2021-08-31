package com.pwc.assignment.dao;

import com.pwc.assignment.dao.mapper.ProjectMapper;
import com.pwc.assignment.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class ProjectDao implements DaoOperations<Project> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Project> getEntities(int size, int offset) {
        return jdbcTemplate.query("select id, name, description,  added_by, added_date from projects "
                        + " order by id desc limit ? offset ?;",
                new ProjectMapper(), size, offset);
    }

    @Override
    public List<Project> getEntities(int size, int offset, String name) {
        return jdbcTemplate.query("select id, name, description,  added_by, added_date from projects "
                        + " where name ilike concat('%',?,'%') order by id desc limit ? offset ?;",
                new ProjectMapper(), size, offset);
    }

    @Override
    public boolean isPresent(Integer id) {
        Integer result = jdbcTemplate.queryForObject("select count(*) from projects where id = ? ;", Integer.class, id);
        return result != null && result > 0;
    }

    @Override
    public Project getById(Integer id) {
        return jdbcTemplate.queryForObject("select id, name, description, added_by, added_date from projects "
                        + " where id = ?;",
                new ProjectMapper(), id);
    }

    @Override
    public Project insertEntity(Project project) {

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement("insert into projects (name , description , added_by) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setInt(3, project.getAddedBy());
            return statement;
        }, holder);

        project.setId(Integer.valueOf(holder.getKeys().get("id").toString()));
        project.setAddedDate(Timestamp.valueOf(holder.getKeys().get("added_date").toString()).toLocalDateTime());

        return project;
    }

    @Override
    public void updateEntity(Project project) {
        jdbcTemplate.update("update projects set  name = ? , description = ?  WHERE id = ? ",
                project.getName(), project.getDescription(), project.getId());
    }

    @Override
    public void deleteEntity(Integer id) {
        jdbcTemplate.update("delete from projects where id = ? ", id);
    }

}
