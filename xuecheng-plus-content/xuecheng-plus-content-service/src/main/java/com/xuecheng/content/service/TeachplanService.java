package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;

import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程基本信息管理业务接口
 * @date 2022/9/6 21:42
 */
public interface TeachplanService {

    /**
     * @param courseId 课程id
     * @return List<TeachplanDto>
     * @description 查询课程计划树型结构
     * @author Mr.M
     * @date 2022/9/9 11:13
     */
    List<TeachplanDto> findTeachplanTree(long courseId);

    /**
     * @description 只在课程计划
     * @param teachplanDto  课程计划信息
     * @return void
     * @author Mr.M
     * @date 2022/9/9 13:39
     */
    void saveTeachplan(SaveTeachplanDto teachplanDto);

    /**
     * 删除teacheplan
     * 删除第一级别的大章节时要求大章节下边没有小章节时方可删除。
     * 删除第二级别的小章节的同时需要将teachplan_media表关联的信息也删除。
     * @param teachplanId teachplanId
     */
    void deleteTeachplan(Long teachplanId);

    void movedownTeachplan(Long teachplanId);

    void moveupTeachplan(Long teachplanId);

}
