package com.xuecheng.content.mapper;


import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TeachplanMapperTests {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Test
    void testCourseBaseMapper() {
        List<TeachplanDto> teachplans = teachplanMapper.selectTreeNodes(117L);
        Assertions.assertNotNull(teachplans);
    }
}
