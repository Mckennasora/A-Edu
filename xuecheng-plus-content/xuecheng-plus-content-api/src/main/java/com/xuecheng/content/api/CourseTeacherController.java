package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.SaveCouresTeacherDto;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程教师编辑接口
 * @date 2022/9/6 11:29
 */
@Api(value = "课程教师编辑接口", tags = "课程教师编辑接口")
@RestController
public class CourseTeacherController {

    @Autowired
    CourseTeacherService courseTeacherService;

    @ApiOperation("查询该课程教师列表")
    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacher> addCourseTeacher(@PathVariable Long courseId) {
        return courseTeacherService.queryCourseTeacherList(courseId);
    }

    @ApiOperation("课程教师创建")
    @PostMapping("/courseTeacher")
    public CourseTeacher saveCourseTeacher(@RequestBody SaveCouresTeacherDto saveCouresTeacherDto) {
        return courseTeacherService.saveCourseTeacher(saveCouresTeacherDto);
    }

    @ApiOperation("课程教师修改")
    @PutMapping("/courseTeacher")
    public CourseTeacher updateCourseTeacher(@RequestBody CourseTeacher courseTeacher) {
        return courseTeacherService.updateCourseTeacher(courseTeacher);
    }


    @ApiOperation("课程教师删除")
    @DeleteMapping("/courseTeacher/course/{courseId}/{teacherId}")
    public void deleteTeachplan(@PathVariable Long courseId, @PathVariable Long teacherId) {
        courseTeacherService.deleteCourseTeacher(courseId, teacherId);
    }
}
