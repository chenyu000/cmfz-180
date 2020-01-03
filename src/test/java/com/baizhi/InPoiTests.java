package com.baizhi;

import com.baizhi.dao.StudentMapper;
import com.baizhi.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InPoiTests {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void contextLoads() throws Exception {
        // 1. 创建excle文件 并且 读入对应的 excle
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("D:/student.xls"));

        //2. 获得sheet 工作簿
        HSSFSheet sheet = workbook.getSheet("学生信息");

        //3. 获取最后一行的下标
        int lastRowNum = sheet.getLastRowNum();
        ArrayList<Student> list = new ArrayList<>();
        for (int i = 1; i <= lastRowNum; i++) {
            String id = sheet.getRow(i).getCell(0).getStringCellValue();
            String name = sheet.getRow(i).getCell(1).getStringCellValue();
            int age = (int) sheet.getRow(i).getCell(2).getNumericCellValue();
            String sex = sheet.getRow(i).getCell(3).getStringCellValue();
            Date bir = sheet.getRow(i).getCell(4).getDateCellValue();
            Student student = new Student(id, name, age, sex, bir, "a", "a");
            list.add(student);
        }
        studentMapper.insertAllStudents(list);
    }

}
