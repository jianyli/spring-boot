package com.li.domain;

import com.li.domain.base.AbstractBeDeleteModel;

import java.util.Date;

public class ExamineInfo extends AbstractBeDeleteModel {
    private Integer businessId;
    private Integer applyUserId;
    private String examineStatus;
    private String examineResult;
    private Date applyTime;
    private Integer examinerId;//当前审核人id
    private String examinerIds;//全部审核人id
    private String remark;

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Integer applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getExamineStatus() {
        return examineStatus;
    }

    public void setExamineStatus(String examineStatus) {
        this.examineStatus = examineStatus;
    }

    public String getExamineResult() {
        return examineResult;
    }

    public void setExamineResult(String examineResult) {
        this.examineResult = examineResult;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getExaminerId() {
        return examinerId;
    }

    public void setExaminerId(Integer examinerId) {
        this.examinerId = examinerId;
    }

    public String getExaminerIds() {
        return examinerIds;
    }

    public void setExaminerIds(String examinerIds) {
        this.examinerIds = examinerIds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
