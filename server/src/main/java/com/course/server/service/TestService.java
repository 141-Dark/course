package com.course.server.service;
import com.course.server.domain.Test;
import com.course.server.domain.TestExample;
import com.course.server.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {
    @Resource
    private TestMapper testMapper;
    public List<Test> list(){
        TestExample testExample = new TestExample();

        //testExample.setOrderByClause("id desc");//根据id倒叙，作为条件
        testExample.createCriteria().andIdEqualTo("2");
        return testMapper.selectByExample(testExample);//根据条件输出
    }
}
