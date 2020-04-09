<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	Message message = new Message();
	message.setTitle(title);
	message.setContent(content);
	
	List<Message> messages = (List<Message>)session.getAttribute("messages");
	if(messages == null){
		messages = new ArrayList<Message>();
		session.setAttribute("messages", messages);
	}
	
	messages.add(message);
	
	response.sendRedirect(request.getContextPath()+"/message.jsp?subFlag=1");
	
%>