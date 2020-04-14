package com.imooc.code;

import com.google.code.kaptcha.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("loginservlet execute");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String code = request.getParameter("code");
        String sessionCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println("code:"+code);
        System.out.println("sessionCode:"+sessionCode);
        if(code!=null && sessionCode!=null){
            if(code.equalsIgnoreCase(sessionCode)){
                out.print("success");
            }
            else{
                out.print("fail");
            }
        }
        out.flush();
        out.close();

    }
}
