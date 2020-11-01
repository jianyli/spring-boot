package com.li.controller;

import com.li.domain.Student;
import com.li.service.IStudentService;
import com.li.support.dto.RestResultDto;
import com.li.support.dto.StudentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Objects;

@Api(tags = "student")
@RestController
@RequestMapping("api/student/")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @ApiOperation("通过学生编号查询学生信息")
    @RequestMapping(value = "findById", method = {RequestMethod.POST, RequestMethod.GET})
    public RestResultDto<Student> findById(@RequestParam Long studentNo) {
        if (studentNo == null) {
            return RestResultDto.newFail("参数不能为空");
        }
        return RestResultDto.newSuccess(studentService.findById(studentNo));
    }

    @ApiOperation("保存学生信息")
    @RequestMapping(value = "save", method = {RequestMethod.POST, RequestMethod.GET})
    public RestResultDto save(@RequestBody Student student) {
        if (Objects.isNull(student)) {
            return RestResultDto.newFail("参数不能为空");
        }
        studentService.save(student);
        return RestResultDto.newSuccess();
    }

    @ApiOperation("查询所有学生信息")
    @RequestMapping(value = "queryAll", method = {RequestMethod.POST, RequestMethod.GET})
    public RestResultDto<List<StudentDto>> queryAll() {
        return RestResultDto.newSuccess(studentService.queryAll());
    }
}
