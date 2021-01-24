package com.li.controller;

import com.li.domain.Student;
import com.li.service.IStudentService;
import com.li.support.dto.RestResultDto;
import com.li.support.dto.StudentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    @ApiOperation("测试异步")
    @RequestMapping(value = "asyncTest", method = {RequestMethod.POST, RequestMethod.GET})
    public RestResultDto asyncTest() throws ExecutionException, InterruptedException {
        Future<String> future = studentService.testAsync();
        if (future.isDone()) {
            return RestResultDto.newSuccess(future.get());
        }
        return RestResultDto.newSuccess("当前未获取异步调用情况");
    }

    @ApiOperation("mergePdf")
    @RequestMapping(value = "mergePdf", method = {RequestMethod.POST, RequestMethod.GET})
    public RestResultDto mergePdf(HttpServletResponse response) throws Exception {
        File file = studentService.mergePdf();
        response.setHeader("Content-Disposition", "attachment;filename=test.pdf");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(FileUtils.readFileToByteArray(file));
//        outputStream.write(new ByteArrayInputStream(new FileOutputStream(file)));
        return RestResultDto.newSuccess();
    }
}
