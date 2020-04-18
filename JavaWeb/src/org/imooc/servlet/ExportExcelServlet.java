package org.imooc.servlet;

import org.apache.poi.ss.usermodel.Workbook;
import org.imooc.service.ExcelService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExportExcelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExcelService service = new ExcelService();
        Workbook workbook = service.export(true);
        response.setHeader("Content-Disposition","attachment;filename=export.xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

    }
}
