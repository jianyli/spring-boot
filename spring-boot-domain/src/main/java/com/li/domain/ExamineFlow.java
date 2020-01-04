package com.li.domain;

import com.li.domain.base.AbstractBeDeleteModel;

public class ExamineFlow extends AbstractBeDeleteModel {
    private Integer examineId;//审批信息id
    private Integer examineUserId;//审批人id
    private int examineOrder;//审批顺序
    private String examineResult;//审批结果
    private String remark;//备注

    public Integer getExamineId() {
        return examineId;
    }

    public void setExamineId(Integer examineId) {
        this.examineId = examineId;
    }

    public Integer getExamineUserId() {
        return examineUserId;
    }

    public void setExamineUserId(Integer examineUserId) {
        this.examineUserId = examineUserId;
    }

    public int getExamineOrder() {
        return examineOrder;
    }

    public void setExamineOrder(int examineOrder) {
        this.examineOrder = examineOrder;
    }

    public String getExamineResult() {
        return examineResult;
    }

    public void setExamineResult(String examineResult) {
        this.examineResult = examineResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
