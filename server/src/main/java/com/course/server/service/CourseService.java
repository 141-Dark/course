package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.domain.CourseContent;
import com.course.server.domain.CourseExample;
import com.course.server.domain.SectionExample;
import com.course.server.dto.CourseContentDto;
import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseContentMapper;
import com.course.server.mapper.CourseMapper;
import com.course.server.mapper.mine.MyCourseMapper;
import com.course.server.utils.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.beans.BeanUtils.*;

@Service
public class CourseService {
    @Resource
    private CourseMapper courseMapper;

    //将MyCourseMapper注入进来(让平级sectionMapper的调用)
    @Resource
    private MyCourseMapper myCourseMapper;

    @Resource
    private CourseCategoryService courseCategoryService;

    @Resource
    private ChapterService chapterService;//注入大章service

    @Resource
    private CourseContentMapper courseContentMapper;//注入课程内容mapper

    public void list(PageDto pageDto){
        //指定当前页码和页面中的数据条数
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        CourseExample courseExample = new CourseExample();

        //设置排列顺序
        SectionExample sectionExample = new SectionExample();
        sectionExample.setOrderByClause("sort asc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);//根据条件输出

        //设置总页数
        PageInfo<Course> pageInfo = new PageInfo<>(courseList);
        pageDto.setTotal(pageInfo.getTotal());

        //完成从数据库实体拷贝到dto实体
        List<CourseDto> courseDtoList = new ArrayList<CourseDto>();
        for (int i = 0;i <courseList.size();i++){
            Course course = courseList.get(i);
            CourseDto courseDto = new CourseDto();
            //完成domian到dto的拷贝
            copyProperties(course,courseDto);
            //将dto中的值添加到courseDtoList列表中
            courseDtoList.add(courseDto);
        }
        //返回数据集
        pageDto.setList(courseDtoList);
    }

    /*
    * 在这里同时实现新增和修改，所以这里要做一点判断（如果id属性有值则认为是修改，没有值则认为是新增）
    * */
    @Transactional
    public void save(CourseDto courseDto){
        //判断id是否为空,如果为空则调用insert方法
        if(StringUtils.isEmpty(courseDto.getId())){
            this.insert(courseDto);
        }else {
            //更新（修改）内容
            this.update(courseDto);
        }
        //保存分类，可能同时有多个分类
        courseCategoryService.save(courseDto.getId(),courseDto.getTree());

    }
    /*
    * 更新(这个方法是service内部使用的，所以不要暴露出去)
    * */
    private void update(CourseDto courseDto){
        Course course = new Course();
        //更改时刷新时间
        Date date = new Date();
        courseDto.setUpdatedAt(date);
        //类型转换
        copyProperties(courseDto,course);

        courseMapper.updateByPrimaryKey(course);

    }
    /*
     * 插入数据
     * 在插入数据时要先将dto转换成dao，在取出数据集时，将dao转为dto
     * 这个方法没有被Controller调用，只被save方法调用过
     * */
    private void insert(CourseDto courseDto){
        //设置id
        courseDto.setId(UuidUtil.getShortId());

        //设置插入时的时间
        Date date = new Date();
        courseDto.setCreatedAt(date);
        courseDto.setUpdatedAt(date);
        Course course = new Course();
        //类型转换
        copyProperties(courseDto,course);

        courseMapper.insert(course);

    }

    public void delete(String id) {
        courseMapper.deleteByPrimaryKey(id);
        //删除课程对应下的大章和小节
        deleteChapterAndSection(id);
    }

    //删除课程对应的大章
    private void deleteChapterAndSection(String courseId){
        chapterService.deleteChaptetAndSection(courseId);
    }

    public void updateTime(String courseId){
        myCourseMapper.updateTime(courseId);
    }

    //加载数据库中的内容(这里的id就是课程id，因为课程表与内容表是一对一的关系，所以可以共用一个id)
    public CourseContentDto findContent(String id){
        CourseContentDto courseContentDto = new CourseContentDto();
        CourseContent courseContent = courseContentMapper.selectByPrimaryKey(id);
        if(courseContent == null) {
            return null;
        }
        //将取到的数据转换为dto
         BeanUtils.copyProperties(courseContent, courseContentDto);
        return courseContentDto;

    }
    //保存内容
    public int saveContent(CourseContentDto courseContentDto){
        CourseContent courseContent = new CourseContent();
        BeanUtils.copyProperties(courseContentDto,courseContent);
        //先进行更新，没有更新到再插入，没有更新到时会返回一个值，根据值来看是否插入
        int i = courseContentMapper.updateByPrimaryKeyWithBLOBs(courseContent);
        if(i == 0){
            courseContentMapper.insert(courseContent);
        }
        return i;
    }
}
