package com.course.business.controller.admin;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.ChapterPageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import com.course.server.utils.ValidatorUtil;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/*
* RequestMapping和PostMapping的区别在于Post指定了前端传递参数的方式必须是Post
* */
@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {
    public static final String BUSINESS_NAME= "大章";
    @Resource
    private ChapterService chapterService;

    @RequestMapping("/list")
    public ResponseDto list(@RequestBody ChapterPageDto chapterPageDto) {
        ValidatorUtil.require(chapterPageDto.getCourseId(),"课程id");//校验课程id是不是空
        chapterService.list(chapterPageDto);
        //新建统一返回值
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(chapterPageDto);//设置返回的数据

        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto){
        //对保存做校验
        ValidatorUtil.require(chapterDto.getName(),"名称");
        ValidatorUtil.require(chapterDto.getCourseId(),"课程id");
        ValidatorUtil.length(chapterDto.getCourseId(),"课程id",1,8);
        //新建统一返回值
        ResponseDto responseDto = new ResponseDto();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    /*
    * PathVariable获取路径上的参数
    * */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        //新建统一返回值
        ResponseDto responseDto = new ResponseDto();

        chapterService.delete(id);

        return responseDto;
    }
}
