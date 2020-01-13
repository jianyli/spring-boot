package com.li.service;

import com.li.domain.ApplyFriend;
import com.li.support.dto.PageDto;
import org.springframework.data.domain.Pageable;

public interface IApplyFriendService {
    void saveOrUpdate(ApplyFriend applyFriend);
    PageDto pageList(Integer applyId, Pageable pageable);
}
