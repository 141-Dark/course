package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.utils.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;
    public void list(PageDto pageDto){
        //指定当前页码和页面中的数据条数
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        ChapterExample chapterExample = new ChapterExample();

        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);//根据条件输出

        //设置总页数
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());

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
        //返回数据集
        pageDto.setList(chapterDtoList);
    }


    /*
    * 插入数据
    * 在插入数据时要先将dto转换成dao，在取出数据集时，将dao转为dto
    * */
    public void save(ChapterDto chapterDto){
        //设置id
        chapterDto.setId(UuidUtil.getShortId());

        Chapter chapter = new Chapter();
        //类型转换
        BeanUtils.copyProperties(chapterDto,chapter);

        chapterMapper.insert(chapter);

    }
}
