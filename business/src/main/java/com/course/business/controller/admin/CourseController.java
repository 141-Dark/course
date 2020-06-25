package com.course.business.controller.admin;
import com.course.server.domain.CourseContent;
import com.course.server.dto.*;
import com.course.server.exception.ValidatorException;
import com.course.server.service.CourseCategoryService;
import com.course.server.service.CourseService;
import com.course.server.utils.ValidatorUtil;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.xml.validation.Validator;
import java.util.List;

/*
* RequestMapping和PostMapping的区别在于Post指定了前端传递参数的方式必须是Post
* */
@RestController
@RequestMapping("/admin/course")
public class CourseController {
    public static final String BUSINESS_NAME= "";
    @Resource
    private CourseService courseService;

    @Resource
    private CourseCategoryService courseCategoryService;

    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        courseService.list(pageDto);
        //新建统一返回值
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(pageDto);//设置返回的数据

        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseDto courseDto){
        //新建统一返回值
        ResponseDto responseDto = new ResponseDto();
        courseService.save(courseDto);
        responseDto.setContent(courseDto);
        return responseDto;
    }

    /*
    * PathVariable获取路径上的参数
    * */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        //新建统一返回值
        ResponseDto responseDto = new ResponseDto();

        courseService.delete(id);

        return responseDto;
    }

    //查询课程下的所有分类
    @PostMapping("/findCategory/{courseId}")
    public ResponseDto findCategory(@PathVariable(value = "courseId")String courseId){
        //
        System.out.println("课程id是："+courseId);
        ResponseDto responseDto = new ResponseDto();
        List<CourseCategoryDto> dtoList = courseCategoryService.findCategory(courseId);
        responseDto.setContent(dtoList);
        System.out.println("执行到这里");
        return responseDto;
    }

    //获取内容
    @GetMapping("/find-content/{id}")
    public ResponseDto findContent(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        CourseContentDto courseContentDto = courseService.findContent(id);
        responseDto.setContent(courseContentDto);
        return responseDto;
    }
    //保存内容
    @PostMapping("/save-content")
    public ResponseDto saveContent(@RequestBody CourseContentDto courseContentDto){
        try{
            ResponseDto responseDto = new ResponseDto();
            courseService.saveContent(courseContentDto);
            return responseDto;
        }
        catch (Exception e){
            throw new ValidatorException("图片太大，请压缩图片");
        }

    }
}
