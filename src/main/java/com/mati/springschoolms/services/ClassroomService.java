package com.mati.springschoolms.services;

import com.mati.springschoolms.entities.Classroom;

import java.util.List;

public interface ClassroomService{
    List<Classroom> findAll();

    void save(Classroom classroom) throws Exception;

    void update(String oldId, Classroom classroom) throws Exception;

    void deleteById(String id) throws Exception;
}
