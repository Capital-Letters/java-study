package org.imooc.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.imooc.dto.ParamDto;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class RequestUtil extends HttpServlet {

    /**
     * 从request流中解析参数与上传文件
     * @param request
     */
    public static ParamDto parseParam(HttpServletRequest request){
        ParamDto result = new ParamDto();
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        upload.setHeaderEncoding("UTF-8");
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem fileItem : fileItems){
                if(fileItem.isFormField()){
                    try {
                        result.getParamMap().put(fileItem.getFieldName(),fileItem.getString("UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println(fileItem.getFieldName());
                    result.getFileMap().put(fileItem.getFieldName(),fileItem);
                    try {
                        fileItem.write(new File("d:/"+fileItem.getFieldName()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return result;
    }
}
