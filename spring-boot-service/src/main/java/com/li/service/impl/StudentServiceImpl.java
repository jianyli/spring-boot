package com.li.service.impl;

import com.google.common.collect.Lists;
import com.li.domain.Student;
import com.li.mapper.StudentMapper;
import com.li.service.IStudentService;
import com.li.support.dto.StudentDto;
import com.li.support.util.TransformUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    private static final String DEFAULT_KEY = "STUDENT_KEY_";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Student findById(Long studentNo) {
        if (studentNo != null) {
            String key = DEFAULT_KEY + studentNo;
            ValueOperations<String,Student> valueOperations = redisTemplate.opsForValue();

            boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey) {
                return valueOperations.get(key);
            }
            //从数据库查
            return studentMapper.findById(studentNo);
        }
        return null;
    }

    @Override
    public void update(Student student) {
        ValueOperations<String,Student> valueOperations = redisTemplate.opsForValue();
        //TODO 更新

        String key = DEFAULT_KEY + student.getStudentNo();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
        }

    }

    @Override
    public void save(Student student) {
        ValueOperations<String,Student> operations = redisTemplate.opsForValue();
        if (!Objects.isNull(student)) {
            studentMapper.save(student);
            Student savedStudent = this.findById(student.getStudentNo());
            operations.set(DEFAULT_KEY + savedStudent.getStudentNo(), savedStudent, 3, TimeUnit.HOURS);
        }
    }

    @Override
    public List<StudentDto> queryAll() {
        List<StudentDto> result = Lists.newArrayList();
        List<Student> students = studentMapper.queryAll();
        students.forEach(x->result.add(TransformUtil.transform(x)));
        return result;
    }
}
