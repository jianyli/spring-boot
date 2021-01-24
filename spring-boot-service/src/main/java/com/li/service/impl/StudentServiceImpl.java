package com.li.service.impl;

import ch.qos.logback.core.util.TimeUtil;
import com.google.common.collect.Lists;
import com.li.domain.Student;
import com.li.mapper.StudentMapper;
import com.li.service.IStudentService;
import com.li.support.dto.StudentDto;
import com.li.support.util.TransformUtil;
import com.li.support.util.XWPFUtil;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;
    private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
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
        long t = System.currentTimeMillis();
        logger.info("查询开始：");
        List<StudentDto> result = Lists.newArrayList();
        List<Student> students = studentMapper.queryAll();
        students.forEach(x->result.add(TransformUtil.transform(x)));
        logger.info("查询结束，耗时：{},当前线程：{}", System.currentTimeMillis() - t, Thread.currentThread().getName());
        Future<String> future = testAsync() ;
        if (future.isDone()) {
            try {
                logger.info("异步调用完成，值为：{}",future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        logger.info("总耗时：{}", System.currentTimeMillis() - t);
        return result;
    }

    @Override
    @Async("asyncExecutor")
    public AsyncResult<String> testAsync() {
        long t = System.currentTimeMillis();
        logger.info("异步线程开始：");
        sleep();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("异步线程结束,耗时:" + (System.currentTimeMillis() - t) + ",线程名称：{}", Thread.currentThread().getName());
        System.out.println("异步线程结束,耗时:" + (System.currentTimeMillis() - t));
        return new AsyncResult<>(Thread.currentThread().getName());
    }

    private void sleep()  {
        System.out.println("线程沉睡开始：");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程沉睡结束");
    }

    @Override
    public File mergePdf() throws Exception {
        File file = new File("test.pdf");
        List<String> filePath = Lists.newArrayList("E:\\test\\a1.pdf","E:\\test\\a2.pdf");
        XWPFUtil.mergePdf(file, filePath);
        return file;
    }
}
