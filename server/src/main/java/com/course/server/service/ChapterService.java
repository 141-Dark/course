package com.course.server.service;
import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.mapper.ChapterMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;
    public List<ChapterDto> list(){
        ChapterExample chapterExample = new ChapterExample();

        //chapterExample.setOrderByClause("id desc");//根据id倒叙，作为条件
        //chapterExample.createCriteria().andIdEqualTo("2");
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);//根据条件输出

        //完成从数据库实体拷贝到dto实体
        List<ChapterDto> chapterDtoList = new ArrayList<ChapterDto>();
        for (int i = 0;i <chapterList.size();i++){
            Chapter chapter = chapterList.get(i);
            ChapterDto chapterDto = new ChapterDto();
            //完成domian到dto的拷贝
            BeanUtils.copyProperties(chapter,chapterDto);
            //将dto中的值添加到chapterDtoList列表中
            chapterDtoList.add(chapterDto);
        }
        return chapterDtoList;
    }
}
