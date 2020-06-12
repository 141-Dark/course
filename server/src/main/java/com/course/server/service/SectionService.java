package com.course.server.service;

import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.SectionPageDto;
import com.course.server.mapper.CourseMapper;
import com.course.server.mapper.SectionMapper;
import com.course.server.utils.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SectionService {
    @Resource
    private SectionMapper sectionMapper;

    //注入courseService
    @Resource
    private CourseService courseService;

    public void list(SectionPageDto sectionPageDto){
        //指定当前页码和页面中的数据条数
        PageHelper.startPage(sectionPageDto.getPage(),sectionPageDto.getSize());
        SectionExample sectionExample = new SectionExample();

        SectionExample.Criteria criteria = sectionExample.createCriteria();
        //进行非空操作
        if(!StringUtils.isEmpty(sectionPageDto.getCourseId())){
            criteria.andCourseIdEqualTo(sectionPageDto.getCourseId());
        }

        if (!StringUtils.isEmpty(sectionPageDto.getChapterId())){
            criteria.andChapterIdEqualTo(sectionPageDto.getChapterId());
        }

        //按顺序排列
        sectionExample.setOrderByClause("sort asc");
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);//根据条件输出

        //设置总页数
        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        sectionPageDto.setTotal(pageInfo.getTotal());

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
        sectionPageDto.setList(sectionDtoList);
    }

    /*
    * 在这里同时实现新增和修改，所以这里要做一点判断（如果id属性有值则认为是修改，没有值则认为是新增）
    * */
    public void save(SectionDto sectionDto) throws ParseException {
        //判断是否为空,如果为空则调用insert方法
        if(StringUtils.isEmpty(sectionDto.getId())){
            this.insert(sectionDto);
        }else {
            //更新（修改）内容
            this.update(sectionDto);
        }

         //调用平级的courseService中的代码实现时间长度的累加（根据课程id来实现累加）
        courseService.updateTime(sectionDto.getCourseId());

    }
    /*
    * 更新(这个方法是service内部使用的，所以不要暴露出去)
    * */
    private void update(SectionDto sectionDto){
        Section section = new Section();
        sectionDto.setUpdatedAt(new Date());
        //类型转换
        BeanUtils.copyProperties(sectionDto,section);

        sectionMapper.updateByPrimaryKey(section);

    }
    /*
     * 插入数据
     * 在插入数据时要先将dto转换成dao，在取出数据集时，将dao转为dto
     * 这个方法没有被Controller调用，只被save方法调用过
     * */
    private void insert(SectionDto sectionDto) throws ParseException {
        //设置id
        sectionDto.setId(UuidUtil.getShortId());
        Section section = new Section();

        //插入数据时添加时间段及时间格式
        Date date = new Date();
        sectionDto.setCreatedAt(date);//创建插入的时间
        sectionDto.setUpdatedAt(date);//修改时间是当前时间

        //对于最新注册的用户都是非会员的，所以要设置视频都是付费的
        //sectionDto.setCharge("否");
        //类型转换
        BeanUtils.copyProperties(sectionDto,section);

        sectionMapper.insert(section);

    }

    public void delete(String id) {
        sectionMapper.deleteByPrimaryKey(id);
    }


}
