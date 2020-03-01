<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: root
  Date: 20-2-28
  Time: 下午5:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    response.addCookie(new Cookie("a", "1"));
    response.addCookie(new Cookie("b", "d"));
    response.addCookie(new Cookie("c", URLEncoder.encode("完美", "UTF-8")));
%>