package com.li.mapper;

import com.li.domain.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface  StudentMapper {
    Student findById(Long studentNo);

    void save(Student student);

    List<Student> queryAll();
}
