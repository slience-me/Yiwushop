package xyz.slienceme.project_shop.utils;

import lombok.Data;

@Data
public class TaskScheduleModel {

    /**
     * 所选作业类型:
     * 1  -> 每天
     * 2  -> 每月
     * 3  -> 每周
     * 4  ->间隔（每隔2个小时，每隔30分钟）
     */
    Integer jobType;

    /**
     * 一周的哪几天
     */
    Integer[] dayOfWeeks;

    /**
     * 一个月的哪几天
     */
    Integer[] dayOfMonths;

    /**
     * 秒
     */
    Integer second;

    /**
     * 分
     */
    Integer minute;

    /**
     * 时
     */
    Integer hour;

    public TaskScheduleModel() {
    }

    public TaskScheduleModel(Integer jobType, Integer[] dayOfWeeks, Integer[] dayOfMonths, Integer second, Integer minute, Integer hour) {
        this.jobType = jobType;
        this.dayOfWeeks = dayOfWeeks;
        this.dayOfMonths = dayOfMonths;
        this.second = second;
        this.minute = minute;
        this.hour = hour;
    }
}
