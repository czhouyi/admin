<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>后台管理登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="utils/css/ext/css/ext-all-neptune.css">
    <script type="text/javascript" charset="utf-8" src="utils/js/ext/bootstrap.js"></script>
    <script type="text/javascript" charset="utf-8" src="app/login.js"></script>
    <script type="text/javascript" charset="utf-8" src="utils/js/ext/ext-lang-zh_CN.js"></script>
</head>
<%@ page import="com.chinadaas.gsinfo.admin.util.StringUtil" %>
<%
	String loginUser = StringUtil.getString(session.getAttribute("login_userName"));
	if(StringUtil.isNull(loginUser)) {
		loginUser = StringUtil.getString(request.getRemoteUser());
	}
	if(!StringUtil.isNull(loginUser)) {
		response.sendRedirect("home");
	}
%>
<body>
</body>
</html>

