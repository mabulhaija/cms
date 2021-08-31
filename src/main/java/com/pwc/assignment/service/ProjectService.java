package com.pwc.assignment.service;

import com.pwc.assignment.dao.ProjectDao;
import com.pwc.assignment.model.Project;
import com.pwc.assignment.service.response.error.exception.client.BadRequestException;
import com.pwc.assignment.service.response.error.exception.client.ForbiddenException;
import com.pwc.assignment.service.response.error.exception.client.NotFoundException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements ModelService<Project> {
    @Autowired
    ProjectDao projectDao;

    @Override
    public List<Project> getEntities(int size, int offset, String name) {
        if (Strings.isBlank(name)) {
            return projectDao.getEntities(size, offset);
        }
        return projectDao.getEntities(size, offset, name);
    }

    @Override
    public Project getEntityById(Integer id) throws NotFoundException, ForbiddenException {
        isPresent(id);
        return projectDao.getById(id);
    }

    @Override
    public boolean isPresent(Integer id) throws NotFoundException {
        if (!projectDao.isPresent(id)) {
            throw new NotFoundException(String.format("Invalid project id %s", id));
        }

        return true;
    }

    @Override
    public Project insertEntity(Project project) {
        return projectDao.insertEntity(project);
    }

    @Override
    public void updateEntity(Integer id, Project project) throws BadRequestException {
        isPresent(id);
        project.setId(id);
        projectDao.updateEntity(project);
    }

    @Override
    public void deleteEntity(Integer id) throws NotFoundException, ForbiddenException {
        isPresent(id);
        projectDao.deleteEntity(id);
    }
}
