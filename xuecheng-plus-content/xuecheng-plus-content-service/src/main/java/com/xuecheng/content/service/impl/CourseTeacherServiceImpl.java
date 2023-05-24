package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.model.dto.SaveCouresTeacherDto;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程计划service接口实现类
 * @date 2022/9/9 11:14
 */
@Service
public class CourseTeacherServiceImpl implements CourseTeacherService {


    @Autowired
    CourseTeacherMapper courseTeacherMapper;


    @Override
    public List<CourseTeacher> queryCourseTeacherList(Long courseId) {
        LambdaQueryWrapper<CourseTeacher> courseTeacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        courseTeacherLambdaQueryWrapper.eq(CourseTeacher::getCourseId, courseId);
        return courseTeacherMapper.selectList(courseTeacherLambdaQueryWrapper);
    }

    @Override
    public CourseTeacher saveCourseTeacher(SaveCouresTeacherDto saveCouresTeacherDto) {
        CourseTeacher courseTeacher = new CourseTeacher();
        BeanUtils.copyProperties(saveCouresTeacherDto, courseTeacher);
        int insert = courseTeacherMapper.insert(courseTeacher);
        return courseTeacher;
    }

    @Override
    public CourseTeacher updateCourseTeacher(CourseTeacher courseTeacher) {
        int i = courseTeacherMapper.updateById(courseTeacher);
        return courseTeacher;
    }

    @Override
    public void deleteCourseTeacher(Long courseId, Long teacherId) {
        courseTeacherMapper.deleteById(teacherId);
    }
}
