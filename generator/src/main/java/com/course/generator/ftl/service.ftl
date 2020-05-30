package com.course.server.service;

import com.course.server.domain.${Domain};
import com.course.server.domain.${Domain}Example;
import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.${Domain}Mapper;
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
public class ${Domain}Service {
@Resource
private ${Domain}Mapper ${domain}Mapper;
public void list(PageDto pageDto){
//指定当前页码和页面中的数据条数
PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
${Domain}Example ${domain}Example = new ${Domain}Example();

List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);//根据条件输出

    //设置总页数
    PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        pageDto.setTotal(pageInfo.getTotal());

        //完成从数据库实体拷贝到dto实体
        List<${Domain}Dto> ${domain}DtoList = new ArrayList<${Domain}Dto>();
                for (int i = 0;i <${domain}List.size();i++){
                ${Domain} ${domain} = ${domain}List.get(i);
                ${Domain}Dto ${domain}Dto = new ${Domain}Dto();
                //完成domian到dto的拷贝
                BeanUtils.copyProperties(${domain},${domain}Dto);
                //将dto中的值添加到${domain}DtoList列表中
                ${domain}DtoList.add(${domain}Dto);
                }
                //返回数据集
                pageDto.setList(${domain}DtoList);
                }

                /*
                * 在这里同时实现新增和修改，所以这里要做一点判断（如果id属性有值则认为是修改，没有值则认为是新增）
                * */
                public void save(${Domain}Dto ${domain}Dto){
                //判断是否为空,如果为空则调用insert方法
                if(StringUtils.isEmpty(${domain}Dto.getId())){
                this.insert(${domain}Dto);
                }else {
                //更新（修改）内容
                this.update(${domain}Dto);
                }
                }
                /*
                * 更新(这个方法是service内部使用的，所以不要暴露出去)
                * */
                private void update(${Domain}Dto ${domain}Dto){
                ${Domain} ${domain} = new ${Domain}();
                //类型转换
                BeanUtils.copyProperties(${domain}Dto,${domain});

                ${domain}Mapper.updateByPrimaryKey(${domain});

                }
                /*
                * 插入数据
                * 在插入数据时要先将dto转换成dao，在取出数据集时，将dao转为dto
                * 这个方法没有被Controller调用，只被save方法调用过
                * */
                private void insert(${Domain}Dto ${domain}Dto){
                //设置id
                ${domain}Dto.setId(UuidUtil.getShortId());

                ${Domain} ${domain} = new ${Domain}();
                //类型转换
                BeanUtils.copyProperties(${domain}Dto,${domain});

                ${domain}Mapper.insert(${domain});

                }

                public void delete(String id) {
                ${domain}Mapper.deleteByPrimaryKey(id);
                }
                }
