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
		Member member = new Member();
		String mid = request.getParameter("mid");
		String code = request.getParameter("code");
		String rand = (String) session.getAttribute("rand");
		member.setMid(mid);
		member.setPassword(PasswordUtil.encoder(request.getParameter("password")));
		String msg = "登录失败，请重新登录";
		String Path = basePath + "login.jsp";
		if (rand.equalsIgnoreCase(code)) {

			try {

				Map<String, Object> map = service.login(member);
				boolean flag = (Boolean) map.get("flag");
				if (flag) {
					session.setAttribute("mid", mid);
					session.setAttribute("name", map.get("name"));
					session.setAttribute("lastdate", map.get("lastdate"));
					session.setAttribute("rid", map.get("role"));
					session.setAttribute("action", map.get("action"));
					CookieUtil cookieUtil = new CookieUtil(request, response);
					cookieUtil.savaMid(mid);
					msg = "登录成功,欢迎光临";
					Path = basePath + "pages/index.jsp";
				}

			} catch (Exception e) {
				//msg="异常信息";
				msg = e.getCause().getMessage();

			}
		} else {
			msg = "验证码错误";
		}
	%>

	<div>
		<p><%=msg%></p>
		<p>
			<a href="<%=Path%>">继续访问</a>
		</p>
	</div>


</body>
</html>