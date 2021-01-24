package com.li.controller.view;

import com.google.common.collect.Lists;
import com.li.domain.Student;
import com.li.domain.UserFriend;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * @Author ljy
 * @Date 2021/1/24 13:28
 * @description
 */
@Controller
//@RequestMapping( "view")
public class HelloController {

    @RequestMapping(value = "/")
    public String index(ModelMap map) {
        List<Student> students = Lists.newArrayList();
        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();
        s1.setName("li1");
        s2.setName("li2");
        s3.setName("li3");
        s1.setSex("男");
        s2.setSex("男");
        s3.setSex("男");

        students.add(s1);
        students.add(s2);
        students.add(s3);
        map.put("message"," test message");
        map.put("username","李建有");
        map.put("username1","甘密");
        map.put("flag","yes");

        map.put("students", students);
        map.put("date", new Date());

        map.put("books",Lists.newArrayList("sd","fed","fsde"));

        map.put("age",34);
        return "index";
    }
}
