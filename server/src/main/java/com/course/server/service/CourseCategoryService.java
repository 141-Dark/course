package com.course.server.service;

import com.course.server.domain.CategoryExample;
import com.course.server.domain.CourseCategory;
import com.course.server.domain.CourseCategoryExample;
import com.course.server.dto.CategoryDto;
import com.course.server.dto.CourseCategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseCategoryMapper;
import com.course.server.utils.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CourseCategoryService {
    @Resource
    private CourseCategoryMapper courseCategoryMapper;
    public void list(PageDto pageDto){
        //指定当前页码和页面中的数据条数
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        CourseCategoryExample courseCategoryExample = new CourseCategoryExample();

        List<CourseCategory> courseCategoryList = courseCategoryMapper.selectByExample(courseCategoryExample);//根据条件输出

        //设置总页数
        PageInfo<CourseCategory> pageInfo = new PageInfo<>(courseCategoryList);
        pageDto.setTotal(pageInfo.getTotal());

        //完成从数据库实体拷贝到dto实体
        List<CourseCategoryDto> courseCategoryDtoList = new ArrayList<CourseCategoryDto>();
        for (int i = 0;i <courseCategoryList.size();i++){
            CourseCategory courseCategory = courseCategoryList.get(i);
            CourseCategoryDto courseCategoryDto = new CourseCategoryDto();
            //完成domian到dto的拷贝
            BeanUtils.copyProperties(courseCategory,courseCategoryDto);
            //将dto中的值添加到courseCategoryDtoList列表中
            courseCategoryDtoList.add(courseCategoryDto);
        }
        //返回数据集
        pageDto.setList(courseCategoryDtoList);
    }

    /*
    * 在这里同时实现新增和修改，所以这里要做一点判断（如果id属性有值则认为是修改，没有值则认为是新增）
    * */
    public void save(CourseCategoryDto courseCategoryDto){
        //判断是否为空,如果为空则调用insert方法
        if(StringUtils.isEmpty(courseCategoryDto.getId())){
            this.insert(courseCategoryDto);
        }else {
            //更新（修改）内容
            this.update(courseCategoryDto);
        }
    }
    /*
    * 更新(这个方法是service内部使用的，所以不要暴露出去)
    * */
    private void update(CourseCategoryDto courseCategoryDto){
        CourseCategory courseCategory = new CourseCategory();
        //类型转换
        BeanUtils.copyProperties(courseCategoryDto,courseCategory);

        courseCategoryMapper.updateByPrimaryKey(courseCategory);

    }
    /*
     * 插入数据
     * 在插入数据时要先将dto转换成dao，在取出数据集时，将dao转为dto
     * 这个方法没有被Controller调用，只被save方法调用过
     * */
    private void insert(CourseCategoryDto courseCategoryDto){
        //设置id
        courseCategoryDto.setId(UuidUtil.getShortId());

        CourseCategory courseCategory = new CourseCategory();
        //类型转换
        BeanUtils.copyProperties(courseCategoryDto,courseCategory);

        courseCategoryMapper.insert(courseCategory);

    }

    public void delete(String id) {
        courseCategoryMapper.deleteByPrimaryKey(id);
    }

    //保存多个分类
    @Transactional
    public void save(String courseId, List<CategoryDto> dtoList){
        //需要初始化
        CourseCategoryExample example= new CourseCategoryExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        courseCategoryMapper.deleteByExample(example);
        for(int i = 0;i<dtoList.size();i++){
            CategoryDto categoryDto  = dtoList.get(i);
            CourseCategory courseCategory = new CourseCategory();
            //设置默认id
            courseCategory.setId(UuidUtil.getShortId());
            courseCategory.setCourseId(courseId);
            courseCategory.setCategoryId(categoryDto.getId());
            //直接调用本地insert方法
            courseCategoryMapper.insert(courseCategory);
        }
    }

    //查找课程下分类
    public List<CourseCategoryDto> findCategory(String courseId){
        CourseCategoryExample example = new CourseCategoryExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<CourseCategory> courseCategoryList  = courseCategoryMapper.selectByExample(example);

        List<CourseCategoryDto> courseCategoryDtoList = new ArrayList<CourseCategoryDto>();
        for(int i = 0;i < courseCategoryList.size();i++){
            CourseCategory courseCategory = courseCategoryList.get(i);
            CourseCategoryDto courseCategoryDto = new CourseCategoryDto();
            System.out.println("数据："+courseCategoryDto);
            BeanUtils.copyProperties(courseCategory,courseCategoryDto);//完成拷贝
            //添加到list中
            courseCategoryDtoList.add(courseCategoryDto);
        }
        return courseCategoryDtoList;
    }
}
