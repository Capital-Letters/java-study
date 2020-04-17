package org.imooc.service;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.imooc.dto.ImportExcelParamDto;
import org.imooc.dto.ImportExcelResultDto;
import org.imooc.entity.Student;
import org.imooc.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelService {
    public ImportExcelResultDto imp(ImportExcelParamDto dto){
        ImportExcelResultDto result = new ImportExcelResultDto();
        result.setTitle(dto.getTitle());
        List<Student> studentList = new ArrayList<>();
        result.setStudentList(studentList);

        String fileName = null;

        try {
            fileName = FileUtil.save(dto.getExcel(),FileUtil.SAVE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("保存上传文件失败");
        }
        if(fileName != null){
            try {
                System.out.println(FileUtil.SAVE_PATH + fileName);
                Workbook workbook = WorkbookFactory.create(new File(FileUtil.SAVE_PATH + fileName));
                Sheet sheet = workbook.getSheetAt(0);
                int rowNum = sheet.getLastRowNum();
                for(int i=1;i<=rowNum;i++){
                    Row row = sheet.getRow(i);
                    Student student = new Student();
                    studentList.add(student);
                    student.setName(row.getCell(0).getStringCellValue());
                    student.setAge((int)(row.getCell(1).getNumericCellValue()));
                    student.setTime(row.getCell(2).getDateCellValue());
                    System.out.println("姓名"+row.getCell(0).getStringCellValue());
                    System.out.println("年龄"+row.getCell(1).getNumericCellValue());
                    System.out.println("时间"+row.getCell(2).getDateCellValue());
                }
            } catch (IOException e) {
                e.printStackTrace();
                result.setMsg("解析Excel失败");
            } catch (InvalidFormatException e) {
                e.printStackTrace();
                result.setMsg("解析Excel失败");
            }
        }

        return result;
    }
}
