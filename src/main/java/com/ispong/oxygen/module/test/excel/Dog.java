package com.ispong.oxygen.module.test.excel;

import com.ispong.oxygen.common.excel.ExcelType;
import lombok.Data;

import java.util.Date;

@Data
public class Dog {

    @ExcelType(cellName = "任务名称")
    private String tName;

    @ExcelType(cellName = "任务描述")
    private String tDesc;

    @ExcelType(cellName = "工时（h）")
    private Double tWorkHour;

    @ExcelType(cellName = "计划开始时间")
    private Date tPlanStartDate;

    @ExcelType(cellName = "计划截止时间")
    private Date tPlanEndDate;

    @ExcelType(cellName = "执行人（账号）")
    private String executorList;

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettDesc() {
        return tDesc;
    }

    public void settDesc(String tDesc) {
        this.tDesc = tDesc;
    }

    public Double gettWorkHour() {
        return tWorkHour;
    }

    public void settWorkHour(Double tWorkHour) {
        this.tWorkHour = tWorkHour;
    }

    public Date gettPlanStartDate() {
        return tPlanStartDate;
    }

    public void settPlanStartDate(Date tPlanStartDate) {
        this.tPlanStartDate = tPlanStartDate;
    }

    public Date gettPlanEndDate() {
        return tPlanEndDate;
    }

    public void settPlanEndDate(Date tPlanEndDate) {
        this.tPlanEndDate = tPlanEndDate;
    }

    public String getExecutorList() {
        return executorList;
    }

    public void setExecutorList(String executorList) {
        this.executorList = executorList;
    }
}

