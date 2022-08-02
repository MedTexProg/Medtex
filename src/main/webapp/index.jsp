<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<%@include file="Date.jsp"%>
<body>
<%
    String username = (String)session.getAttribute("username");
    if(username == null || "".equals(username)){
%>
<h4>Welcome Guest!</h4><br>
<a href="${pageContext.request.contextPath}/Login.jsp">Log in</a>
<a href="${pageContext.request.contextPath}/Register.jsp">Register</a>
<%
    }
    if(session!=null)
    {
        username = (String)session.getAttribute("username");
    }
    if(username!=null)
    {
%>
<h3>Welcome back <%=username%>! </h3>
<a href="${pageContext.request.contextPath }/LogoutServlet">Logout!</a>
<%
    }
%>
</body>
</html>