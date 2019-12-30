package com.li.controller;

import com.li.domain.UserInfo;
import com.li.service.IUserService;
import com.li.support.dto.RestResultDto;
import com.li.support.dto.UserInfoDTO;
import com.li.support.enums.ErrorCodeEnum;
import com.li.support.enums.UserRoleEnum;
import com.li.support.enums.YesNoEnum;
import com.li.support.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Api(tags = "用户接口")
@RestController
@RequestMapping(value = "cloud/user")
public class UserController {
    @Resource
    private IUserService userService;

    @ApiOperation(value = "保存用户数据", notes = "")
    @RequestMapping(value = "save", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResultDto<Boolean> save(@ApiParam(required = true) @RequestParam("name") String name,
                              @ApiParam(required = true) @RequestParam("password") String password,
                              @ApiParam(required = false) @RequestParam("nickName") String nickName,
                              @ApiParam(required = false) @RequestParam("telephone") String telephone
                     ) {
        if (StringUtils.isAnyBlank(name, password, nickName, telephone)) {
            throw new ServiceException(ErrorCodeEnum.NULL);
        }
        if (!StringUtils.isNumeric(telephone)) {
            throw new ServiceException(ErrorCodeEnum.ILLEGAL);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setPassword(password);
        userInfo.setNickName(nickName);
        userInfo.setTelephone(telephone);
        userInfo.setRole(UserRoleEnum.USER.getKey());
        userInfo.setIsGroup(YesNoEnum.NO.getKey());
        userService.save(userInfo);
        return RestResultDto.newSuccess();
    }

    @ApiOperation(value = "更新用户数据")
    @RequestMapping(value = "update", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResultDto<Boolean> update(@ApiParam(required = true) @RequestParam("id") String id,
                                         @ApiParam(required = false) @RequestParam("name") String name,
                                         @ApiParam(required = false) @RequestParam("password") String password,
                                         @ApiParam(required = false) @RequestParam("nickName") String nickName,
                                         @ApiParam(required = false) @RequestParam("telephone") String telephone,
                                         @ApiParam(required = false) @RequestParam("birthTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthTime,
                                         @ApiParam(required = false) @RequestParam("qqNumber") String qqNumber
                                         ) {
        if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) {
            throw new ServiceException(ErrorCodeEnum.ILLEGAL);
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(Integer.valueOf(id));
        userInfoDTO.setName(name);
        userInfoDTO.setPassword(password);
        userInfoDTO.setNickName(nickName);
        userInfoDTO.setTelephone(telephone);
        userInfoDTO.setBirthTime(birthTime);
        userInfoDTO.setQqNumber(qqNumber);

        userService.update(userInfoDTO);
        return RestResultDto.newSuccess();

    }

    @ApiOperation(value = "查询user")
    @RequestMapping(value = "findById", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResultDto<UserInfoDTO> findById(@ApiParam(required = true) @RequestParam(value = "id") String id) {
        if (StringUtils.isBlank(id)) {
            throw new RuntimeException("id不能为空");
        }
        if (!StringUtils.isNumeric(id)) {
            throw new ServiceException(ErrorCodeEnum.ILLEGAL);
        }
        return RestResultDto.newSuccess(userService.findById(Integer.valueOf(id)));
    }

    @ApiOperation(value = "查询user通过姓名或电话号码")
    @RequestMapping(value = "findUserByNameOrTelephone", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResultDto<UserInfo> findUserByNameOrTelephone(@ApiParam(required = false) @RequestParam(value = "name") String name,
                                                             @ApiParam(required = false) @RequestParam(value = "telephone") String telephone) {
        if (StringUtils.isNotBlank(name) || StringUtils.isNotBlank(telephone)) {
            throw new ServiceException(ErrorCodeEnum.NULL);
        }

        return RestResultDto.newSuccess();
    }

    @ApiOperation(value = "登录测试")
    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResultDto login() {
        return RestResultDto.newFail("没有登录");
    }
}
