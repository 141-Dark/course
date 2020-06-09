import com.course.server.config.ServerApplication;
import com.course.server.domain.Chapter;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.exception.ValidatorException;
import com.course.server.service.ChapterService;
import com.course.server.utils.ValidatorUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class ChapterTest {
    @Autowired
    private ChapterService service;
    @Test
    public void testUpdateOne(){
        /*
        * 修改/增加数据的测试（插入的id在1-6位之内，非空）
        * 目的：防止入侵者跳过前端直接访问后端进行恶意注册和修改
        * */
        ChapterDto dto = new ChapterDto();
        dto.setCourseId("1222");
        dto.setName("junit大章测试");
        try {
            ValidatorUtil.require(dto.getName(),"大章名称");
            ValidatorUtil.length(dto.getCourseId(),"课程id",1,6);
        }catch (ValidatorException e){
            System.out.println(e.getMessage());
        }
        service.save(dto);
        System.out.println("testUpdateOne数据插入成功");
    }
    @Test
    public void testUpdateTwo(){
        /*
         * 修改/增加数据的测试（插入的id在1-6位之内，非空）
         * 目的：防止入侵者跳过前端直接访问后端进行恶意注册和修改
         * */
        ChapterDto dto = new ChapterDto();
        dto.setCourseId("1222222");
        dto.setName("junit大章测试");
        try {
            ValidatorUtil.require(dto.getName(),"大章名称");
            ValidatorUtil.length(dto.getCourseId(),"课程id",1,6);
        }catch (ValidatorException e){
            System.out.println(e.getMessage());
        }
        service.save(dto);
        System.out.println("testUpdateTwo数据插入失败");
    }

    @Test
    public void testDelOne(){
        /*
         * 测试删除数据+
         * */
        try {
            service.delete("9");
        }catch (ValidatorException e){
            System.out.println("没有修改权限");
        }
        System.out.println("数据删除成功");
    }

}
