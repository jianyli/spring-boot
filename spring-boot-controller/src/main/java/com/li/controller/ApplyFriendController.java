package com.li.controller;

import com.li.domain.ApplyFriend;
import com.li.service.IApplyFriendService;
import com.li.support.dto.ApplyFriendDTO;
import com.li.support.dto.PageDto;
import com.li.support.dto.RestResultDto;
import com.li.support.enums.ErrorCodeEnum;
import com.li.support.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@Api(value = "申请好友")
@RestController
@RequestMapping("cloud/applyFriend")
public class ApplyFriendController {
    @Resource
    private IApplyFriendService applyFriendService;

    @ApiOperation(value = "申请")
    @RequestMapping(value = "apply", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResultDto<Boolean> apply(@ApiParam(required = true) @RequestParam("applyId") Integer applyId,
                                        @ApiParam(required = true) @RequestParam("targetId") Integer targetId,
                                        @ApiParam(required = false) @RequestParam("noteName") String noteName,
                                        @ApiParam(required = true) @RequestParam("groupId") Integer groupId,
                                        @ApiParam(required = false) @RequestParam("reason") String reason) {
        if (applyId == null || targetId == null || groupId == null || applyId==targetId) {
            throw new ServiceException(ErrorCodeEnum.NULL);
        }
        ApplyFriend applyFriend = new ApplyFriend();
        applyFriend.setApplyId(applyId);
        applyFriend.setTargetId(targetId);
        applyFriend.setNoteName(noteName);
        applyFriend.setGroupId(groupId);
        applyFriend.setReason(reason);

        applyFriendService.save(applyFriend);
        return RestResultDto.newSuccess(true);
    }

    @ApiOperation("分页查询")
    @RequestMapping(value = "pageList", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResultDto<PageDto<ApplyFriendDTO>> pageList(@ApiParam(name = "applyId",value = "申请者id",required = true) @RequestParam("applyId") Integer applyId,
                                                           @ApiParam(name = "page",value = "页码，从0开始",required = true) @RequestParam("page") Integer page,
                                                           @ApiParam(name = "size",value = "每页大小",required = true) @RequestParam("size") Integer size) {
        if (Objects.isNull(applyId) || Objects.isNull(page) || Objects.isNull(size)) {
            throw new ServiceException(ErrorCodeEnum.NULL);
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(new Sort.Order(Sort.Direction.DESC,"apply_time")));
        return RestResultDto.newSuccess(applyFriendService.pageList(applyId, pageable));
    }
}
