package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程计划树型结构dto
 * @date 2022/9/9 10:27
 */
@Data
@ToString
public class TeachplanDto extends Teachplan {

    //课程计划关联的媒资信息
    TeachplanMedia teachplanMedia;

    //子结点
    List<TeachplanDto> teachPlanTreeNodes;

}
