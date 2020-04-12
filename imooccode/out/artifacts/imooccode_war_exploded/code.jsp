<%@ page import="com.imooc.code.CaptcahCode" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/4/12
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //1：清空浏览器缓存,目的是为了清空浏览器的缓存，因为浏览器
    //会对网站的资源文件和图像进行记忆存储，如果被浏览器加载过的图片就记忆起来，记忆以后
    //文件就不会和服务器在交互，如果我们验证不清空的话可能会造成一个问题就是：验证刷新以后没有效果。
    response.setHeader("pragma","no-cache");
    response.setHeader("cache-control","no-cache");
    response.setHeader("expires","0");

    String code = CaptcahCode.drawImageVerificate(response);
    session.setAttribute("code",code);

    //如何解决outputstream异常问题
    out.clear();
    out = pageContext.pushBody();
%>
