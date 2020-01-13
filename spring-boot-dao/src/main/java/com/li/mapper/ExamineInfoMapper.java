package com.li.mapper;

import com.li.domain.ExamineInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface ExamineInfoMapper {
    final static String BEEN_DELETE = " and been_delete=0";
    @Insert("insert into t_examine_info(business_id, examine_type, applyUserId, applyTime, examiner_Id, examiners_id,  create_time, been_delete) " +
            "VALUE (#{businessId}, #{examineType}, #{applyUserId},now(), #{examinerId}, #{examinerIds}, now(), 0)")
    void save(ExamineInfo examineInfo);

    @Select("select id from t_examine_info where business_id=#{businessId} and examine_type=#{examineType}" + BEEN_DELETE)
    Integer findId(Integer businessId, String examineType);
}
