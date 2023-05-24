package com.xuecheng.content.model.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Mr.M
 * @version 1.0
 * @description 保存课程计划dto，包括新增、修改
 * @date 2022/9/9 10:27
 */
@Data
@ToString
public class SaveCouresTeacherDto implements Serializable {


    private static final long serialVersionUID = 1L;


    /**
     * 课程标识
     */
    private Long courseId;

    /**
     * 教师标识
     */
    private String teacherName;

    /**
     * 教师职位
     */
    private String position;

    /**
     * 教师简介
     */
    private String introduction;
}
