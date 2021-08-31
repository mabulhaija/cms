package com.pwc.assignment.service;

import com.pwc.assignment.dao.DepartmentDao;
import com.pwc.assignment.model.Department;
import com.pwc.assignment.model.SystemUser;
import com.pwc.assignment.service.response.error.exception.client.BadRequestException;
import com.pwc.assignment.service.response.error.exception.client.ForbiddenException;
import com.pwc.assignment.service.response.error.exception.client.NotFoundException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements ModelService<Department> {
    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    UserService userService;

    @Override
    public List<Department> getEntities(int size, int offset, String name) {
        if (Strings.isBlank(name)) {
            return departmentDao.getEntities(size, offset);
        }
        return departmentDao.getEntities(size, offset, name);
    }

    @Override
    public Department getEntityById(Integer id) throws NotFoundException, ForbiddenException {
        isPresent(id);
        return departmentDao.getById(id);
    }

    @Override
    public boolean isPresent(Integer id) throws NotFoundException {

        if (!departmentDao.isPresent(id)) {
            throw new NotFoundException(String.format("Invalid department id %s", id));
        }

        return true;
    }

    @Override
    public Department insertEntity(Department department) {

        return departmentDao.insertEntity(department);
    }

    @Override
    public void updateEntity(Integer id, Department department) throws BadRequestException {
        isPresent(id);
        department.setId(id);
        departmentDao.updateEntity(department);
    }

    @Override
    public void deleteEntity(Integer id) throws NotFoundException, ForbiddenException {
        isPresent(id);
        departmentDao.deleteEntity(id);
    }

    public List<SystemUser> getDepartmentUsers(Integer departmentId, int size, int offset) {
        isPresent(departmentId);
        return userService.getDepartmentUsers(departmentId, size, offset);
    }

}
