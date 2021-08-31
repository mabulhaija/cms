package com.pwc.assignment.dao;

import java.util.List;

public interface DaoOperations<T> {

    List<T> getEntities(int size, int offset);

    List<T> getEntities(int size, int offset, String name);

    boolean isPresent(Integer id);

    T getById(Integer id);

    T insertEntity(T t);

    void updateEntity(T t);

    void deleteEntity(Integer id);
}

