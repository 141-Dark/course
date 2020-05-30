package com.course.server.service;

import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.SectionMapper;
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
public class SectionService {
@Resource
private SectionMapper sectionMapper;
public void list(PageDto pageDto){
//指定当前页码和页面中的数据条数
PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
SectionExample sectionExample = new SectionExample();

List<Section> sectionList = sectionMapper.selectByExample(sectionExample);//根据条件输出

    //设置总页数
    PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        pageDto.setTotal(pageInfo.getTotal());

        //完成从数据库实体拷贝到dto实体
        List<SectionDto> sectionDtoList = new ArrayList<SectionDto>();
                for (int i = 0;i <sectionList.size();i++){
                Section section = sectionList.get(i);
                SectionDto sectionDto = new SectionDto();
                //完成domian到dto的拷贝
                BeanUtils.copyProperties(section,sectionDto);
                //将dto中的值添加到sectionDtoList列表中
                sectionDtoList.add(sectionDto);
                }
                //返回数据集
                pageDto.setList(sectionDtoList);
                }

                /*
                * 在这里同时实现新增和修改，所以这里要做一点判断（如果id属性有值则认为是修改，没有值则认为是新增）
                * */
                public void save(SectionDto sectionDto){
                //判断是否为空,如果为空则调用insert方法
                if(StringUtils.isEmpty(sectionDto.getId())){
                this.insert(sectionDto);
                }else {
                //更新（修改）内容
                this.update(sectionDto);
                }
                }
                /*
                * 更新(这个方法是service内部使用的，所以不要暴露出去)
                * */
                private void update(SectionDto sectionDto){
                Section section = new Section();
                //类型转换
                BeanUtils.copyProperties(sectionDto,section);

                sectionMapper.updateByPrimaryKey(section);

                }
                /*
                * 插入数据
                * 在插入数据时要先将dto转换成dao，在取出数据集时，将dao转为dto
                * 这个方法没有被Controller调用，只被save方法调用过
                * */
                private void insert(SectionDto sectionDto){
                //设置id
                sectionDto.setId(UuidUtil.getShortId());

                Section section = new Section();
                //类型转换
                BeanUtils.copyProperties(sectionDto,section);

                sectionMapper.insert(section);

                }

                public void delete(String id) {
                sectionMapper.deleteByPrimaryKey(id);
                }
                }
