package com.pwc.assignment.service;

import com.pwc.assignment.dao.UserDao;
import com.pwc.assignment.model.Role;
import com.pwc.assignment.model.SystemUser;
import com.pwc.assignment.model.authentication.AuthenticationUser;
import com.pwc.assignment.service.response.error.exception.client.BadRequestException;
import com.pwc.assignment.service.response.error.exception.client.ForbiddenException;
import com.pwc.assignment.service.response.error.exception.client.NotFoundException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService , ModelService<SystemUser> {
    @Autowired
    UserDao userDao;
    @Autowired
    DepartmentService departmentService;

    @Override
    public List<SystemUser> getEntities(int size, int offset, String name) {
        if (Strings.isBlank(name)) {
            return userDao.getEntities(size, offset);
        }
        return userDao.getEntities(size, offset, name);
    }

    @Override
    public SystemUser getEntityById(Integer id) throws NotFoundException, ForbiddenException {
        isPresent(id);
        return userDao.getById(id);
    }

    @Override
    public boolean isPresent(Integer id) throws NotFoundException {
        if (!userDao.isPresent(id)) {
            throw new NotFoundException(String.format("Invalid user id %s", id));
        }

        return true;
    }

    @Override
    public SystemUser insertEntity(SystemUser systemUser) {
        departmentService.isPresent(systemUser.getDepartmentId());
        return userDao.insertEntity(systemUser);
    }

    @Override
    public void updateEntity(Integer id, SystemUser systemUser) throws BadRequestException {
        departmentService.isPresent(systemUser.getDepartmentId());
        isPresent(id);
        systemUser.setId(id);
        userDao.updateEntity(systemUser);
    }

    @Override
    public void deleteEntity(Integer id) throws NotFoundException, ForbiddenException {
        isPresent(id);
        userDao.deleteEntity(id);
    }


    public List<SystemUser> getDepartmentUsers(Integer departmentId, int size, int offset) {
        departmentService.isPresent(departmentId);
        return userDao.getDepartmentUsers(departmentId, size, offset);
    }

    @Override
    public AuthenticationUser loadUserByUsername(String userName) throws UsernameNotFoundException {
        SystemUser user;
        String passwordCache;
        try {
            user = userDao.findByUsername(userName);
            passwordCache = user.getPassword();
            user.setPassword(null);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found username: " + userName);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        if (user.getRole().equals(Role.MANAGER)) {
            grantedAuthorities.add(new SimpleGrantedAuthority(Role.EMPLOYEE.name()));
        }
        return new AuthenticationUser(user.getUserName(), passwordCache,
                grantedAuthorities, user);
    }

    public boolean isUserExists(String username) {

        SystemUser user = userDao.findByUsername(username);

        if (user == null) {
            throw new NotFoundException(String.format("Invalid system user name %s", username));
        }

        return true;
    }
    public SystemUser getUserByUserName(String username) throws NotFoundException, ForbiddenException {
        isUserExists(username);
        return userDao.getUserByUserName(username);
    }
}
