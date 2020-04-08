package com.imooc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.domain.User;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<User> list = (List<User>) this.getServletContext().getAttribute("list");
		for(User u : list) {
			if(u.getUsername().equals(username)) {
				if(u.getPassword().equals(password)) {
					String remember = request.getParameter("remember");
					if("true".equals(remember)) {
						Cookie cookie = new Cookie("username",username);
						cookie.setPath("/reg_login");
						cookie.setMaxAge(60*60*24);
						response.addCookie(cookie);
					}
					
					
					
					request.getSession().setAttribute("user", u);
					response.sendRedirect("/reg_login/success.jsp");
					return;
				}
			}
		}
		System.out.println("用户名或密码错误");
		request.setAttribute("msg", "用户名或密码错误");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
