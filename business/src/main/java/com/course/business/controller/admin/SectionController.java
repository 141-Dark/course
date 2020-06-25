package com.course.business.controller.admin;
import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.dto.SectionPageDto;
import com.course.server.service.SectionService;
import com.course.server.utils.ValidatorUtil;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.xml.validation.Validator;
import java.text.ParseException;

/*
* RequestMapping和PostMapping的区别在于Post指定了前端传递参数的方式必须是Post
* */
@RestController
@RequestMapping("/admin/section")
public class SectionController {
    public static final String BUSINESS_NAME= "";
    @Resource
    private SectionService sectionService;

    @RequestMapping("/list")
    public ResponseDto list(@RequestBody SectionPageDto sectionPageDto) {
        //判断课程id和大章id不能为空
        ValidatorUtil.require(sectionPageDto.getCourseId(),"课程id");
        ValidatorUtil.require(sectionPageDto.getChapterId(),"大章id");

        sectionService.list(sectionPageDto);
        //新建统一返回值
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(sectionPageDto);//设置返回的数据

        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody SectionDto sectionDto) throws ParseException {
        //对保存做校验
        ValidatorUtil.require(sectionDto.getTitle(),"小节名称");
        ValidatorUtil.require(sectionDto.getCourseId(),"课程id");
        ValidatorUtil.require(sectionDto.getChapterId(),"大章id");
        ValidatorUtil.length(sectionDto.getCourseId(),"课程id",1,8);
        ValidatorUtil.length(sectionDto.getTitle(),"课程名",1,8);
        //新建统一返回值
        ResponseDto responseDto = new ResponseDto();
        sectionService.save(sectionDto);
        responseDto.setContent(sectionDto);
        return responseDto;
    }

    /*
    * PathVariable获取路径上的参数
    * */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        //新建统一返回值
        ResponseDto responseDto = new ResponseDto();

        sectionService.delete(id);

        return responseDto;
    }
}
