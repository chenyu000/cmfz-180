package com.baizhi.controller;

import com.baizhi.dao.StudentMapper;
import com.baizhi.entity.Student;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/poi")
public class PoiController {
    @Autowired
    private StudentMapper studentMapper;

    /*
     *       导出
     * */
    @RequestMapping("poiOut")
    public void poiOut(HttpServletResponse response) throws IOException {
        List<Student> list = studentMapper.queryAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setDataFormat(format);

        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("微软雅黑");
        font.setBold(true);
        font.setColor(Font.COLOR_RED);

        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //2. 创建 工作簿
        HSSFSheet sheet = workbook.createSheet("学生信息");
        sheet.setColumnWidth(4, 20 * 256);
        //3. 创建行
        HSSFRow row = sheet.createRow(0);
        //自定义标题行
        String[] titles = {"编号", "姓名", "年龄", "性别", "生日"};
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(title);
            cell.setCellStyle(cellStyle);
        }

        for (int i = 0; i < list.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(list.get(i).getId());
            row1.createCell(1).setCellValue(list.get(i).getName());
            row1.createCell(2).setCellValue(list.get(i).getAge());
            row1.createCell(3).setCellValue(list.get(i).getSex());
            HSSFCell cell = row1.createCell(4);
            cell.setCellValue(list.get(i).getBir());
            cell.setCellStyle(cellStyle1);
        }
        response.setHeader("content-disposition", "attachment;filename=stu.xls");
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
        /*workbook.write(new FileOutputStream("D:/ooo.xls"));*/
    }

    /*
     *
     *   导入
     * */
    @RequestMapping("poiIn")
    public void poiIn(MultipartFile myExcle) throws IOException {
        //1. 接收用户上传的excle 的流
        InputStream inputStream = myExcle.getInputStream();
        //2. 获得excle文件
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        //3. 获得工作簿
        HSSFSheet sheet = workbook.getSheet("学生信息");
        //4. 根据工作簿获取到最后一行
        ArrayList<Student> list = new ArrayList<>();
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            String id = sheet.getRow(i).getCell(0).getStringCellValue();
            String name = sheet.getRow(i).getCell(1).getStringCellValue();
            double c = sheet.getRow(i).getCell(2).getNumericCellValue();
            Integer age = Integer.valueOf((int) c);
            String sex = sheet.getRow(i).getCell(3).getStringCellValue();
            Date bir = sheet.getRow(i).getCell(4).getDateCellValue();
            Student student = new Student(id, name, age, sex, bir, "a", "a");
            list.add(student);
        }
        for (Student student : list) {
            System.out.println(student);
        }
    }

    @RequestMapping("showAllStudents")
    public List<Student> showAllStudents() {
        return studentMapper.queryAll();
    }
}
