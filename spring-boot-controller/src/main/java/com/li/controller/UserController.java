package com.li.controller;

import com.li.domain.UserInfo;
import com.li.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@Api(tags = "用户接口")
@RestController
@RequestMapping(value = "dev/user")
public class UserController {
    @Resource
    private IUserService userService;

    @ApiOperation(value = "保存用户数据", notes = "")
    @RequestMapping(value = "save", method = {RequestMethod.GET, RequestMethod.POST})
    public void save(@RequestBody UserInfo userInfo) {
        if (Objects.isNull(userInfo)) {
            throw new RuntimeException("参数不能为空");
        }
        userService.save(userInfo);
    }

    @ApiOperation(value = "查询user")
    @RequestMapping(value = "findById", method = {RequestMethod.GET, RequestMethod.POST})
    public UserInfo findById(@ApiParam(required = true) @RequestParam(value = "id") String id) {
        if (StringUtils.isBlank(id)) {
            throw new RuntimeException("id不能为空");
        }
        return userService.findById(id);
    }
}
