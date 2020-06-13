package com.course.business.controller.admin;
import com.course.server.domain.Category;
import com.course.server.dto.CategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CategoryService;
import com.course.server.utils.ValidatorUtil;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.xml.validation.Validator;
import java.util.List;

/*
* RequestMapping和PostMapping的区别在于Post指定了前端传递参数的方式必须是Post
* */
@RestController
@RequestMapping("/admin/category")
public class CategoryController {
    public static final String BUSINESS_NAME= "";
    @Resource
    private CategoryService categoryService;

    @RequestMapping("/all")
    public ResponseDto all() {
        //新建统一返回值
        ResponseDto responseDto = new ResponseDto();

        List<CategoryDto> categoryDtos =categoryService.all();

        responseDto.setContent(categoryDtos);//设置返回的数据

        return responseDto;
    }@RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        categoryService.list(pageDto);
        //新建统一返回值
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(pageDto);//设置返回的数据

        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody CategoryDto categoryDto){
        //对保存做校验

        //新建统一返回值
        ResponseDto responseDto = new ResponseDto();
        categoryService.save(categoryDto);
        responseDto.setContent(categoryDto);
        return responseDto;
    }

    /*
    * PathVariable获取路径上的参数
    * */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        //新建统一返回值
        ResponseDto responseDto = new ResponseDto();

        categoryService.delete(id);

        return responseDto;
    }
}
