package com.course.server.mapper.mine;

import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

public interface MyCourseMapper {
    int updateTime(@Param("courseId")String courseId);
}
