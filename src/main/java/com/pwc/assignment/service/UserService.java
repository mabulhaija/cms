package com.pwc.assignment.service;

import com.pwc.assignment.dao.UserDao;
import com.pwc.assignment.model.SystemUser;
import com.pwc.assignment.service.response.error.exception.client.BadRequestException;
import com.pwc.assignment.service.response.error.exception.client.ForbiddenException;
import com.pwc.assignment.service.response.error.exception.client.NotFoundException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements ModelService<SystemUser> {
    @Autowired
    UserDao userDao;

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
        return userDao.insertEntity(systemUser);
    }

    @Override
    public void updateEntity(Integer id, SystemUser systemUser) throws BadRequestException {
        systemUser.setId(id);
        isPresent(id);
        userDao.updateEntity(systemUser);
    }

    @Override
    public void deleteEntity(Integer id) throws NotFoundException, ForbiddenException {
        isPresent(id);
        userDao.deleteEntity(id);
    }


    public List<SystemUser> getDepartmentUsers(Integer departmentId, int size, int offset) {
        return userDao.getDepartmentUsers(departmentId, size, offset);
    }
}
