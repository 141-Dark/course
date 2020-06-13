package com.course.server.service;

import com.course.server.domain.Category;
import com.course.server.domain.CategoryExample;
import com.course.server.dto.CategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CategoryMapper;
import com.course.server.utils.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    public List<CategoryDto> all(){
        //指定当前页码和页面中的数据条数
        CategoryExample categoryExample = new CategoryExample();

        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);//根据条件输出

        //完成从数据库实体拷贝到dto实体
        List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
        for (int i = 0;i <categoryList.size();i++){
            Category category = categoryList.get(i);
            CategoryDto categoryDto = new CategoryDto();
            //完成domian到dto的拷贝
            BeanUtils.copyProperties(category,categoryDto);
            //将dto中的值添加到categoryDtoList列表中
            categoryDtoList.add(categoryDto);
        }
        //返回数据集
        return categoryDtoList;
    }
public void list(PageDto pageDto){
        //指定当前页码和页面中的数据条数
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        CategoryExample categoryExample = new CategoryExample();

        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);//根据条件输出

        //设置总页数
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        pageDto.setTotal(pageInfo.getTotal());

        //完成从数据库实体拷贝到dto实体
        List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
        for (int i = 0;i <categoryList.size();i++){
            Category category = categoryList.get(i);
            CategoryDto categoryDto = new CategoryDto();
            //完成domian到dto的拷贝
            BeanUtils.copyProperties(category,categoryDto);
            //将dto中的值添加到categoryDtoList列表中
            categoryDtoList.add(categoryDto);
        }
        //返回数据集
        pageDto.setList(categoryDtoList);
    }

    /*
    * 在这里同时实现新增和修改，所以这里要做一点判断（如果id属性有值则认为是修改，没有值则认为是新增）
    * */
    public void save(CategoryDto categoryDto){
        //判断是否为空,如果为空则调用insert方法
        if(StringUtils.isEmpty(categoryDto.getId())){
            this.insert(categoryDto);
        }else {
            //更新（修改）内容
            this.update(categoryDto);
        }
    }
    /*
    * 更新(这个方法是service内部使用的，所以不要暴露出去)
    * */
    private void update(CategoryDto categoryDto){
        Category category = new Category();
        //类型转换
        BeanUtils.copyProperties(categoryDto,category);

        categoryMapper.updateByPrimaryKey(category);

    }
    /*
     * 插入数据
     * 在插入数据时要先将dto转换成dao，在取出数据集时，将dao转为dto
     * 这个方法没有被Controller调用，只被save方法调用过
     * */
    private void insert(CategoryDto categoryDto){
        //设置id
        categoryDto.setId(UuidUtil.getShortId());

        Category category = new Category();
        //类型转换
        BeanUtils.copyProperties(categoryDto,category);

        categoryMapper.insert(category);

    }


    //如果是删除父节点，则连同子节点一起删除
    public void delete(String id) {
        deleteChild(id);
        categoryMapper.deleteByPrimaryKey(id);
    }

    private void deleteChild(String id){
        Category category = categoryMapper.selectByPrimaryKey(id);
        //如果是父节点，则要删除对应的子节点
        if("000".equals(category.getParent())){
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.createCriteria().andParentEqualTo(category.getId());
            categoryMapper.deleteByExample(categoryExample);
        }
    }
}
