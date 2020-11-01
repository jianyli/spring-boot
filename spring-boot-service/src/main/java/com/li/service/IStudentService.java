package com.li.service;

import com.li.domain.Student;
import com.li.support.dto.StudentDto;

import java.util.List;

public interface IStudentService {
    Student findById(Long studentNo);
    void update(Student student);
    void save(Student student);
    List<StudentDto> queryAll();
}
