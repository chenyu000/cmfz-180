package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.dao.StudentMapper;
import com.baizhi.dao.TeacherMapper;
import com.baizhi.entity.Student;
import com.baizhi.entity.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasypoiInTests {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    /*
     *   一对一 导入
     * */
    @Test
    public void contextLoads() throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);   //标题行
        params.setHeadRows(1);    //表头
        List<Student> list = ExcelImportUtil.importExcel(
                new FileInputStream("D:/student.xls"),
                Student.class, params);

        for (Student student : list) {
            System.out.println(student);
        }
    }

    /*
     *
     *   一对多 导入
     * */
    @Test
    public void test() throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);   //标题行
        params.setHeadRows(2);    //表头
        List<Teacher> list = ExcelImportUtil.importExcel(
                new FileInputStream("D:/xiyoujihouzhuan.xls"),
                Teacher.class, params);
        for (Teacher teacher : list) {
            System.out.println(teacher.getList());
        }
    }

}
