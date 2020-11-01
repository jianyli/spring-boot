package com.li.mapper;

import com.li.domain.ExamineFlow;
import com.li.support.dto.ExamineFlowDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

public interface ExamineFlowMapper {
    @Insert("insert into t_examine_flow(examine_id, examine_user_id, examine_order, create_time, been_delete) VALUE (#{examineId}, #{examineUserId}, #{examineOrder}, now(), 0)")
    void save(ExamineFlow examineFlow);
}
