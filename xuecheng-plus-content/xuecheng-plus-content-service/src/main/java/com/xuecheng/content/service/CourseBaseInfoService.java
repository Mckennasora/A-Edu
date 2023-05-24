package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程基本信息管理业务接口
 * @date 2022/9/6 21:42
 */
public interface CourseBaseInfoService {

    /*
     * @description 课程查询接口
     * @param pageParams 分页参数
     * @param queryCourseParamsDto 条件条件
     * @return com.xuecheng.base.model.PageResult<com.xuecheng.content.model.po.CourseBase>
     * @author Mr.M
     * @date 2022/9/6 21:44
     */
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

    /**
     * @param companyId    教学机构id
     * @param addCourseDto 课程基本信息
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 添加课程基本信息
     * @author Mr.M
     * @date 2022/9/7 17:51
     */
    CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);

    /**
     * @param courseId courseId
     * @return CourseBaseInfoDto
     */
    //根据课程id查询课程基本信息，包括基本信息和营销信息
    CourseBaseInfoDto getCourseBaseInfo(Long courseId);


    /**
     * @param companyId     教学机构id
     * @param editCourseDto 课程基本信息
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 修改课程基本信息
     * @author Mr.M
     * @date 2022/9/7 17:51
     */
    CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto editCourseDto);

    void deleteCourseInfo(Long courseId);
}
