package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.mapper.TeachplanMediaMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.TeachplanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程计划service接口实现类
 * @date 2022/9/9 11:14
 */
@Service
public class TeachplanServiceImpl implements TeachplanService {

    static final int FIRST_GRADE = 1;
    static final int SECOND_GRADE = 2;

    @Autowired
    TeachplanMapper teachplanMapper;

    @Autowired
    TeachplanMediaMapper teachplanMediaMapper;

    @Override
    public List<TeachplanDto> findTeachplanTree(long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }

    @Transactional
    @Override
    public void saveTeachplan(SaveTeachplanDto teachplanDto) {

        //课程计划id
        Long id = teachplanDto.getId();
        //修改课程计划
        if (id != null) {
            Teachplan teachplan = teachplanMapper.selectById(id);
            BeanUtils.copyProperties(teachplanDto, teachplan);
            teachplanMapper.updateById(teachplan);
        } else {
            //取出同父同级别的课程计划数量
            int count = getTeachplanCount(teachplanDto.getCourseId(), teachplanDto.getParentid());
            Teachplan teachplanNew = new Teachplan();
            //设置排序号
            teachplanNew.setOrderby(count + 1);
            BeanUtils.copyProperties(teachplanDto, teachplanNew);

            teachplanMapper.insert(teachplanNew);
        }
    }

    @Transactional
    @Override
    public void deleteTeachplan(Long teachplanId) {
        Teachplan teachplan = teachplanMapper.selectById(teachplanId);
        if (teachplan.getGrade() == FIRST_GRADE) {
            LambdaQueryWrapper<Teachplan> teachplanLambdaQueryWrapper = new LambdaQueryWrapper<>();
            teachplanLambdaQueryWrapper.eq(Teachplan::getParentid, teachplanId);
            Integer integer = teachplanMapper.selectCount(teachplanLambdaQueryWrapper);
            if (integer > 0) {
                XueChengPlusException.cast("课程计划信息还有子级信息，无法操作");
            }
            teachplanMapper.deleteById(teachplanId);
        } else if (teachplan.getGrade() == SECOND_GRADE) {
            teachplanMapper.deleteById(teachplanId);
            teachplanMediaMapper.deleteById(teachplanId);
        }
    }

    @Transactional
    @Override
    public void movedownTeachplan(Long teachplanId) {
        Teachplan teachplan = teachplanMapper.selectById(teachplanId);
        Long courseId = teachplan.getCourseId();
        Integer orderNow = teachplan.getOrderby();
        if (teachplan.getGrade() == FIRST_GRADE) {
            teachplanMove(teachplan, courseId, orderNow, 0L,false);
        } else if (teachplan.getGrade() == SECOND_GRADE) {
            teachplanMove(teachplan, courseId, orderNow, teachplan.getParentid(),false);
        }
    }

    @Transactional
    @Override
    public void moveupTeachplan(Long teachplanId) {
        Teachplan teachplan = teachplanMapper.selectById(teachplanId);
        Long courseId = teachplan.getCourseId();
        Integer orderNow = teachplan.getOrderby();
        if (teachplan.getGrade() == FIRST_GRADE) {
            teachplanMove(teachplan, courseId, orderNow, 0L,true);
        } else if (teachplan.getGrade() == SECOND_GRADE) {
            teachplanMove(teachplan, courseId, orderNow, teachplan.getParentid(),true);
        }
    }

    private void teachplanMove(Teachplan teachplan, Long courseId, Integer orderNow, Long parentId,boolean moveUp) {
        int orderAfter = orderNow;
        if(moveUp){
            if (orderNow == 1) {
                XueChengPlusException.cast("已在最前，无法上移");
            }
            orderAfter --;
        }else {
            int count = getTeachplanCount(courseId, parentId);
            if (orderNow == count) {
                XueChengPlusException.cast("已在最后，无法下移");
            }
            orderAfter ++;
        }
        LambdaQueryWrapper<Teachplan> teachplanLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teachplanLambdaQueryWrapper.eq(Teachplan::getParentid, parentId);
        teachplanLambdaQueryWrapper.eq(Teachplan::getCourseId, courseId);
        teachplanLambdaQueryWrapper.eq(Teachplan::getOrderby, orderAfter);
        Teachplan one = teachplanMapper.selectOne(teachplanLambdaQueryWrapper);
        one.setOrderby(orderNow);
        teachplanMapper.updateById(one);
        teachplan.setOrderby(orderAfter);
        teachplanMapper.updateById(teachplan);
    }

    /**
     * @param courseId 课程id
     * @param parentId 父课程计划id
     * @return int 最新排序号
     * @description 获取最新的排序号
     * @author Mr.M
     * @date 2022/9/9 13:43
     */
    private int getTeachplanCount(long courseId, long parentId) {
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId, courseId);
        queryWrapper.eq(Teachplan::getParentid, parentId);
        return teachplanMapper.selectCount(queryWrapper);
    }
}
