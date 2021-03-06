package org.imooc.servlet;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.imooc.dto.ImportWordParamDto;
import org.imooc.dto.ImportWordResultDto;
import org.imooc.dto.ParamDto;
import org.imooc.service.WordService;
import org.imooc.util.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImportWordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(request)) {
            ParamDto dto = RequestUtil.parseParam(request);
            ImportWordParamDto importWordParamDto = new ImportWordParamDto();
            importWordParamDto.setTitle(dto.getParamMap().get("title"));
            importWordParamDto.setWord(dto.getFileMap().get("word"));
            WordService service = new WordService();
            ImportWordResultDto resultDto = service.imp(importWordParamDto);
            request.setAttribute("result",resultDto);
        }
        else{

        }
        request.getRequestDispatcher("WEB-INF/jsp/importWordResult.jsp").forward(request,response);

    }
}
