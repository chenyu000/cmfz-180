package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.StudentMapper;
import com.baizhi.dao.TeacherMapper;
import com.baizhi.entity.Student;
import com.baizhi.entity.Teacher;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasypoiOutTests {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    /*
     *   带有图片导出
     * */
    @Test
    public void contextLoads() throws IOException {
        List<Student> list = studentMapper.queryAll();

        for (Student student : list) {
            /*F:\后期项目\last_project\cmfz-180\src\main\webapp\img\*/
            student.setHead_img("F:\\后期项目\\last_project\\cmfz-180\\src\\main\\webapp\\img\\" + student.getHead_img());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("西游记里的学生", "学生"),
                Student.class, list);
        workbook.write(new FileOutputStream("D:/easypoi.xls"));
        workbook.close();
    }

    /*
     *
     *   一对多导出
     * */
    @Test
    public void test() throws Exception {
        List<Teacher> teachers = teacherMapper.queryAll();

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("西游记啊啊啊", "测试"),
                Teacher.class, teachers);
        workbook.write(new FileOutputStream("D:/xiyoujihouzhuan.xls"));
        workbook.close();
    }

}
