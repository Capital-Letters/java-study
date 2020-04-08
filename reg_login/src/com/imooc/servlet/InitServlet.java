package com.imooc.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.imooc.domain.User;

/**
 * 用户注册初始化的Servlet
 * 
 */


public class InitServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		
		this.getServletContext().setAttribute("list", list);;
	}
}
