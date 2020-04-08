package com.imooc.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.imooc.domain.User;
import com.imooc.utils.UploadUtils;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建磁盘文件项工厂对象
		//创建核心解析类
		//解析request请求，返回的是list集合，list集合中存放FileItem对象
		try {
			Map<String,String> map = new HashMap<String,String>();
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
			List<FileItem> list = servletFileUpload.parseRequest(request);
			List<String> hobbyList = new ArrayList<String>();
			String url = null ;
			for(FileItem fileItem:list) {
				if(fileItem.isFormField()) {
					String name = fileItem.getFieldName();
					String value = fileItem.getString("UTF-8");
					System.out.println(name+":"+value);
					if("hobby".equals(name)) {
						String hobbyValue = fileItem.getString("UTF-8");
						hobbyList.add(hobbyValue);
						hobbyValue = hobbyList.toString().substring(1, hobbyList.toString().length()-1);
						System.out.println(hobbyValue);
						map.put(name,hobbyValue);
					}
					else {
						map.put(name, value);
					}
					
				}
				else {
					String fileName = fileItem.getName();
					if(fileName != null && !"".equals(fileName)) {
						String uuidFileName = UploadUtils.getUUIDFileName(fileName);
						InputStream is = fileItem.getInputStream();
						String path = this.getServletContext().getRealPath("/upload");
						url = path + "\\" + uuidFileName ;
						OutputStream os = new FileOutputStream(url);
						int len = 0;
						byte[] b = new byte[1024];
						while((len = is.read(b))!=-1) {
							os.write(b, 0, len);
						}
						is.close();
						os.close();
					}
					
				}
				
			}
			System.out.println(map);
			List<User>  userList = (List<User>) this.getServletContext().getAttribute("list");
			for(User u : userList) {
				if(u.getUsername().equals(map.get("username"))) {
					request.setAttribute("msg", "用户名已存在");
					request.getRequestDispatcher("/regist.jsp").forward(request, response);
					return;
				}
			}
			User user = new User();
			user.setUsername(map.get("username"));
			user.setPassword(map.get("password"));
			user.setSex(map.get("sex"));
			user.setNickname(map.get("nickname"));
			user.setHobby(map.get("hobby"));
			user.setPath(url);
			
			
			userList.add(user);
			
			this.getServletContext().setAttribute("list", userList);
			for(User u : (List<User>)this.getServletContext().getAttribute("list")) {
				System.out.println(u);
			}
			request.getSession().setAttribute("username",user.getUsername() );
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
