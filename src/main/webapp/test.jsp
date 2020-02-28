<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: root
  Date: 20-2-28
  Time: 下午5:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    System.out.println("request.getCookies() = " + Arrays.toString(request.getCookies()));
    String a = request.getParameter("a");
    System.out.println("a = " + a);
    String ua = request.getHeader("user-agent");
    System.out.println("ua = " + ua);
    String contentType = response.getContentType();
    System.out.println("contentType = " + contentType);
%>