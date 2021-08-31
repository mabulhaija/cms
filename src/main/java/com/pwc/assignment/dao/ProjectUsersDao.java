package com.pwc.assignment.dao;

import com.pwc.assignment.dao.mapper.ProjectUsersMapper;
import com.pwc.assignment.model.ProjectUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class ProjectUsersDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<ProjectUsers> getEntities(Integer projectId, int size, int offset) {

        return jdbcTemplate.query("select project_id,user_id,project_name,user_name,added_by,added_date from project_users "
                        + " where project_id = ? order by id desc limit ? offset ?;",
                new ProjectUsersMapper(), projectId, size, offset);
    }


    public boolean isPresent(Integer projectId, Integer userId) {
        Integer result = jdbcTemplate.queryForObject("select count(*) from project_users where project_id = ? and user_id=?;", Integer.class, projectId, userId);
        return result != null && result > 0;
    }


    public ProjectUsers insertEntity(ProjectUsers projectUsers) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement("insert into project_users (project_id , user_id ,added_by, project_name, user_name) values (?,?,?,(SELECT name from projects where id = ?),(SELECT user_name from system_users where id = ?))", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, projectUsers.getProjectId());
            statement.setInt(2, projectUsers.getUserId());
            statement.setInt(3, projectUsers.getAddedBy());
            statement.setInt(4, projectUsers.getProjectId());
            statement.setInt(5, projectUsers.getUserId());
            return statement;
        }, holder);

        projectUsers.setAddedDate(Timestamp.valueOf(holder.getKeys().get("added_date").toString()).toLocalDateTime());

        return projectUsers;
    }

    public void deleteEntity(Integer projectId,Integer userId) {
        jdbcTemplate.update("delete from departments where project_id = ? and user_id = ? ", projectId, userId);
    }
}
