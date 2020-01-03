package com.baizhi;

import com.baizhi.dao.StudentMapper;
import com.baizhi.entity.Student;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OutPoiTests {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void contextLoads() {
        //1. 创建 excle 文件
        HSSFWorkbook workbook = new HSSFWorkbook();

        //2. 创建工作簿
        HSSFSheet sheet = workbook.createSheet("sheet1");

        //3. 创建行 0: 代表的是第一行
        HSSFRow row = sheet.createRow(0);

        //4. 创建 单元格
        HSSFCell cell = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);

        //5. 单元格设值
        cell.setCellValue("Hello");
        cell1.setCellValue(true);
        cell2.setCellValue(10.0);
        //6. 指定excle 输出的位置以及文件名
        try {
            workbook.write(new File("D:/test.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void contextLoads1() throws IOException {

        //1.excle文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //
        HSSFFont font = workbook.createFont();
        //加粗
        font.setBold(true);
        //红色
        font.setColor(Font.COLOR_RED);
        //字体
        font.setFontName("楷体");
        font.setFontHeightInPoints((short) 20);

        //自定义日期类型
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        //单元格的样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();

        cellStyle.setDataFormat(format);
        cellStyle.setFont(font);
        //居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //2.工作簿
        HSSFSheet sheet = workbook.createSheet("每一秒");
        //设值单元格的左右长度
        /*
         *  java后台于excle  需要 *256
         * */
        sheet.setColumnWidth(0, 70 * 256);
        //3.行
        HSSFRow row = sheet.createRow(0);
        //4.单元格
        HSSFCell cell = row.createCell(0);
        //5.设值
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);
        //6.写出
        workbook.write(new File("D:/myCat.xls"));
        workbook.close();
    }

    @Test
    public void test() throws IOException {

        //1. 创建 excle文件
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

        List<Student> list = studentMapper.queryAll();
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
        workbook.write(new File("D:/student.xls"));
        workbook.close();
    }
}
