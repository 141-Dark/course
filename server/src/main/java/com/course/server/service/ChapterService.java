package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.ChapterPageDto;
import com.course.server.exception.ValidatorException;
import com.course.server.mapper.ChapterMapper;
import com.course.server.utils.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;
    public void list(ChapterPageDto chapterPageDto){
        //指定当前页码和页面中的数据条数
        PageHelper.startPage(chapterPageDto.getPage(),chapterPageDto.getSize());
        ChapterExample chapterExample = new ChapterExample();

        //ChapterExample.Criteria cri= chapterExample.createCriteria();
        //先判断传进来的值是不是空值
        if(!StringUtils.isEmpty(chapterPageDto.getCourseId())){
            //根据id输出(chapterExample.createCriteria()只可以使用一次，在多条件查询时可以将它提取出来，如上)
           chapterExample.createCriteria().andCourseIdEqualTo(chapterPageDto.getCourseId());
        }

        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);//根据条件输出

        //设置总页数
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        chapterPageDto.setTotal(pageInfo.getTotal());

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
        chapterPageDto.setList(chapterDtoList);
    }

    /*
    * 在这里同时实现新增和修改，所以这里要做一点判断（如果id属性有值则认为是修改，没有值则认为是新增）
    * */
    public void save(ChapterDto chapterDto){
        //判断是否为空,如果为空则调用insert方法
        if(StringUtils.isEmpty(chapterDto.getId())){
            this.insert(chapterDto);
        }else {
            //更新（修改）内容
            this.update(chapterDto);
        }
    }
    /*
    * 更新(这个方法是service内部使用的，所以不要暴露出去)
    * */
    private void update(ChapterDto chapterDto){
        Chapter chapter = new Chapter();
        //类型转换
        BeanUtils.copyProperties(chapterDto,chapter);

        chapterMapper.updateByPrimaryKey(chapter);

    }
    /*
     * 插入数据
     * 在插入数据时要先将dto转换成dao，在取出数据集时，将dao转为dto
     * 这个方法没有被Controller调用，只被save方法调用过
     * */
    private void insert(ChapterDto chapterDto){
        //设置id
        chapterDto.setId(UuidUtil.getShortId());

        Chapter chapter = new Chapter();
        //类型转换
        BeanUtils.copyProperties(chapterDto,chapter);

        chapterMapper.insert(chapter);

    }

    public void delete(String id) {
        chapterMapper.deleteByPrimaryKey(id);
    }
}
