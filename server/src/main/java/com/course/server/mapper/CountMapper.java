package com.course.server.mapper;

import com.course.server.domain.Count;
import com.course.server.domain.CountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CountMapper {
    long countByExample(CountExample example);

    int deleteByExample(CountExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(Count record);

    int insertSelective(Count record);

    List<Count> selectByExampleWithBLOBs(CountExample example);

    List<Count> selectByExample(CountExample example);

    Count selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") Count record, @Param("example") CountExample example);

    int updateByExampleWithBLOBs(@Param("record") Count record, @Param("example") CountExample example);

    int updateByExample(@Param("record") Count record, @Param("example") CountExample example);

    int updateByPrimaryKeySelective(Count record);

    int updateByPrimaryKeyWithBLOBs(Count record);
}