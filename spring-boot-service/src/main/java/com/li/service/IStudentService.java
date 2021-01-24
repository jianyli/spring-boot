package com.li.service;

import com.li.domain.Student;
import com.li.support.dto.StudentDto;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.File;
import java.util.List;

public interface IStudentService {
    Student findById(Long studentNo);
    void update(Student student);
    void save(Student student);
    List<StudentDto> queryAll();
    AsyncResult<String> testAsync();

    File mergePdf() throws Exception;
}
