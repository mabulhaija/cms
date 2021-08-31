package com.pwc.assignment.service;

import com.pwc.assignment.dao.ProjectUsersDao;
import com.pwc.assignment.model.ProjectUsers;
import com.pwc.assignment.service.response.error.exception.client.BadRequestException;
import com.pwc.assignment.service.response.error.exception.client.ForbiddenException;
import com.pwc.assignment.service.response.error.exception.client.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectUserService {

    @Autowired
    ProjectUsersDao projectUsersDao;


    public List<ProjectUsers> getEntities(Integer projectId, int size, int offset) {

        return projectUsersDao.getEntities(projectId, size, offset);
    }



    public boolean isPresent(Integer projectId, Integer userId) throws NotFoundException {

        if (!projectUsersDao.isPresent(projectId, userId)) {
            throw new NotFoundException(String.format("Invalid projectId and/or userId "));
        }
        return true;

    }

    public ProjectUsers insertEntity(ProjectUsers projectUsers) {
        if (isPresent(projectUsers.getProjectId(), projectUsers.getUserId())) {
            throw new BadRequestException("user already assigned");
        }
        return projectUsersDao.insertEntity(projectUsers);
    }


    public void deleteEntity(Integer projectId, Integer userId) throws NotFoundException, ForbiddenException {
        projectUsersDao.deleteEntity(projectId, userId);
    }
}
