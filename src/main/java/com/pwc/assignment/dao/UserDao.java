package com.pwc.assignment.dao;

import com.pwc.assignment.dao.mapper.UserMapper;
import com.pwc.assignment.model.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class UserDao implements DaoOperations<SystemUser> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public List<SystemUser> getEntities(int size, int offset) {
        return jdbcTemplate.query("select id,user_name,department_id,department_name,name,email,status,role,added_by,added_date from system_users "
                        + " order by id desc limit ? offset ?;",
                new UserMapper(), size, offset);
    }

    @Override
    public List<SystemUser> getEntities(int size, int offset, String query) {
        return jdbcTemplate.query("select id,user_name,department_id,department_name,name,email,status,role,added_by,added_date from system_users "
                        + " where name ilike concat('%',?,'%') or user_name ilike concat('%',?,'%') or email ilike concat('%',?,'%') order by id desc limit ? offset ?;",
                new UserMapper(), query, query, query, size, offset);
    }

    @Override
    public boolean isPresent(Integer id) {
        Integer result = jdbcTemplate.queryForObject("select count(*) from system_users where id = ? ;", Integer.class, id);
        return result != null && result > 0;
    }

    @Override
    public SystemUser getById(Integer id) {
        return jdbcTemplate.queryForObject("select id,user_name,department_id,department_name,name,email,status,role,added_by,added_date from system_users "
                        + " where id = ?;",
                new UserMapper(), id);
    }

    @Override
    public SystemUser insertEntity(SystemUser systemUser) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement("insert into system_users (user_name,password,department_id,name,email,role,added_by,department_name) values (?,?,?,?,?,?,?,(SELECT name from departments where id =?))", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, systemUser.getUserName());
            statement.setString(2, bcryptEncoder.encode(systemUser.getPassword()));
            statement.setInt(3, systemUser.getDepartmentId());
            statement.setString(4, systemUser.getName());
            statement.setString(5, systemUser.getEmail());
            statement.setString(6, systemUser.getRole().name());
            statement.setInt(7, systemUser.getAddedBy());
            statement.setInt(8, systemUser.getDepartmentId());

            return statement;
        }, holder);

        systemUser.setId(Integer.valueOf(holder.getKeys().get("id").toString()));
        systemUser.setAddedDate(Timestamp.valueOf(holder.getKeys().get("added_date").toString()).toLocalDateTime());

        return systemUser;
    }

    @Override
    public void updateEntity(SystemUser systemUser) {

        jdbcTemplate.update("update system_users set  user_name = ? , department_id = ?, name = ?," +
                        "email = ?, role = ?, department_name = (SELECT name from departments where id = ?) " +
                        "  WHERE id = ? ",

                systemUser.getUserName(), systemUser.getDepartmentId(), systemUser.getName(),
                systemUser.getEmail(), systemUser.getRole().name(), systemUser.getDepartmentId(), systemUser.getId());
    }

    @Override
    public void deleteEntity(Integer id) {
        jdbcTemplate.update("delete from system_users where id = ? ", id);
    }

    public List<SystemUser> getDepartmentUsers(Integer departmentId, int size, int offset) {
        return jdbcTemplate.query("select id,user_name,department_id,department_name,name,email,status,role,added_by,added_date from system_users "
                        + " where department_id =? order by id desc limit ? offset ?;",
                new UserMapper(), departmentId, size, offset);
    }
    public SystemUser findByUsername(String username) {

        SystemUser user = null;
        String sql = "select id,user_name,password,department_id,department_name,name,email,status,role,added_by,added_date from system_users "
                + " where ( user_name=? or email=?)";
        RowMapper<SystemUser> rowMapper = new BeanPropertyRowMapper<>(SystemUser.class);
        try {
            user = jdbcTemplate.queryForObject(sql, rowMapper, username, username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return user;
    }

    public SystemUser getUserByUserName(String username) {
        return jdbcTemplate.queryForObject("select id,user_name,department_id,department_name,name,email,status,role,added_by,added_date from system_users "
                        + " where user_name = ?;",
                new UserMapper(), username);
    }
}
