package com.mati.springschoolms.impl;

import com.mati.springschoolms.entities.Classroom;
import com.mati.springschoolms.repositories.ClassroomRepository;
import com.mati.springschoolms.services.ClassroomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    @Override
    public void save(Classroom classroom) throws Exception{
        if (Objects.equals(classroom.getClassroomName(), ""))
            throw new Exception("The classroom name is required.");
        else if (classroomRepository.existsById(classroom.getClassroomName()))
            throw new Exception("The classroom " + classroom.getClassroomName() + "already exists.");
        else if (classroom.getAvailable() == null)
            throw new Exception("The field available must not be null.");
        else
            classroomRepository.save(classroom);
    }

    @Override
    public void update(String oldId, Classroom classroom) throws Exception{
        if (Objects.equals(classroom.getClassroomName(), "") || classroom.getClassroomName() == null)
            throw new Exception("The classroom name can not be empty.");
        else if (!Objects.equals(oldId, classroom.getClassroomName())){
            if (classroomRepository.existsById(classroom.getClassroomName())){
                throw new Exception("The classroom " + classroom.getClassroomName() + " already exists.");
            } else {
                classroomRepository.deleteById(oldId);
            }
        }
        classroomRepository.save(classroom);
    }

    @Override
    public void deleteById(String id) throws Exception {
        if (!classroomRepository.existsById(id))
            throw new Exception("The classroom " + id + "does not exists.");
        else
            classroomRepository.deleteById(id);
    }

    private ClassroomRepository classroomRepository;
}
