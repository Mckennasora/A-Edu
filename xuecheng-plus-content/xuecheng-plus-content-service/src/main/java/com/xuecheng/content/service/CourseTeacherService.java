package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.SaveCouresTeacherDto;
import com.xuecheng.content.model.po.CourseTeacher;

import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程教师信息管理业务接口
 * @date 2022/9/6 21:42
 */
public interface CourseTeacherService {

    List<CourseTeacher> queryCourseTeacherList(Long courseId);

    CourseTeacher saveCourseTeacher(SaveCouresTeacherDto saveCouresTeacherDto);

    CourseTeacher updateCourseTeacher(CourseTeacher courseTeacher);

    void deleteCourseTeacher(Long courseId, Long teacherId);
}
