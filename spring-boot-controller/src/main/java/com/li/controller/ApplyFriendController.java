package com.li.controller;

import com.li.domain.ApplyFriend;
import com.li.domain.GroupInfo;
import com.li.support.dto.RestResultDto;
import com.li.support.enums.ErrorCodeEnum;
import com.li.support.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "申请好友")
@RestController
public class ApplyFriendController {

    @ApiOperation(value = "申请")
    @RequestMapping(value = "apply", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResultDto<Boolean> apply(@ApiParam(required = true) @RequestParam("applyId") Integer applyId,
                                        @ApiParam(required = true) @RequestParam("targetId") Integer targetId,
                                        @ApiParam(required = false) @RequestParam("noteName") String noteName,
                                        @ApiParam(required = true) @RequestParam("groupId") Integer groupId,
                                        @ApiParam(required = false) @RequestParam("reason") String reason) {
        if (applyId == null || targetId == null || groupId == null) {
            throw new ServiceException(ErrorCodeEnum.NULL);
        }
        ApplyFriend applyFriend = new ApplyFriend();
        applyFriend.setApplyId(applyId);
        applyFriend.setTargetId(targetId);
        applyFriend.setNoteName(noteName);
        applyFriend.setGroupId(groupId);
        applyFriend.setReason(reason);


        //TODO 保存记录到数据库
        return RestResultDto.newSuccess(true);
    }
}
