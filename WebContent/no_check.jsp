<%@page import="cn.mldn.util.CookieUtil"%>
<%@page import="cn.mldn.exception.ProjectException"%>
<%@page import="cn.mldn.exception.login.LoginException"%>
<%@page import="cn.mldn.util.enctype.PasswordUtil"%>
<%@page import="cn.mldn.vo.Member"%>
<%@page import="cn.mldn.factory.Factory"%>
<%@page import="cn.mldn.service.front.IMemberServiceFront"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">


</head>


<body>
	<%
		IMemberServiceFront service = Factory.getInstanece("member.service");
		CookieUtil cookieUtil = new CookieUtil(request, response);
		String mid = cookieUtil.loadMid();
		String Path = basePath + "login.jsp";
		if (mid != null) {
			Map<String, Object> map = service.login(mid);
			boolean flag = (Boolean) map.get("flag");
		try{
				session.setAttribute("mid", mid);
				session.setAttribute("name", map.get("name"));
				session.setAttribute("lastdate", map.get("lastdate"));
				session.setAttribute("rid", map.get("role"));
				session.setAttribute("action", map.get("action"));
				cookieUtil.savaMid(mid);
				Path = basePath + "pages/index.jsp";
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		response.sendRedirect(Path);
	%>

</body>
</html>