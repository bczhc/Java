<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 20-2-28
  Time: 下午5:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String a = request.getParameter("a");
    System.out.println("a = " + a);
    String method = request.getMethod();
    System.out.println(method);
%>