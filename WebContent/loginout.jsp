<%@page import="cn.mldn.util.CookieUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<%
	CookieUtil cookieUtil = new CookieUtil(request, response);
	cookieUtil.cleaMid();//清除
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String loginUrl = basePath + "login.jsp";
%>
<base href="<%=basePath%>">

<body>
	<%
		session.invalidate();
	%>
	<h1>用户注销成功</h1>

	<h1>
		<a href="<%=loginUrl%>">继续访问</a>
	</h1>
</body>
</html>