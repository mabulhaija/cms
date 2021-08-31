package com.pwc.assignment.service;

import com.pwc.assignment.service.response.error.exception.client.BadRequestException;
import com.pwc.assignment.service.response.error.exception.client.ForbiddenException;
import com.pwc.assignment.service.response.error.exception.client.NotFoundException;

import java.util.List;

public interface ModelService<T> {

    List<T> getEntities(int size, int offset, String name);

    T getEntityById(Integer id) throws NotFoundException, ForbiddenException;

    boolean isPresent(Integer id) throws NotFoundException;

    T insertEntity(T t);

    void updateEntity(Integer id, T t) throws BadRequestException;

    void deleteEntity(Integer id) throws NotFoundException, ForbiddenException;

}
