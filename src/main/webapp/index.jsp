<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>后台管理系统</title>
    <!-- Ext -->
    <script type="text/javascript" src="utils/js/ext/ext-all.js"></script>
    <script type="text/javascript" src="utils/js/ext/ext-lang-zh_CN.js"></script>
   	<script type="text/javascript" src="utils/js/jquery/jquery-1.7.2.min.js"></script> 
   	<script type="text/javascript" src="utils/js/security/gsinfo.js"></script>
    <script type="text/javascript" src="utils/js/security/security-min_v2.js"></script>
    <script type="text/javascript" src="utils/js/kind/kindeditor-all-min.js"></script>
    
    <link rel="stylesheet" type="text/css" href="utils/css/ext/css/ext-all-neptune.css">
    <link rel="stylesheet" type="text/css" href="utils/css/ext/css/ext-patch.css">
    <link rel="stylesheet" type="text/css" href="resources/css/index.css">
    
    <!-- page specific -->
    <script type="text/javascript" src="layouts/basic.js"></script>
    <script type="text/javascript" src="layouts/Center.js"></script> 
    <script type="text/javascript" src="app/app.js"></script>
</head>
<%@ page import="com.chinadaas.gsinfo.admin.util.StringUtil" %>
<%
	String loginUser = StringUtil.getString(session
			.getAttribute("login_userName"));
	if (StringUtil.isNull(loginUser)) {
		loginUser = StringUtil.getString(request.getRemoteUser());
	}
	if (StringUtil.isNull(loginUser)) {
		response.sendRedirect("login.jsp");
	}
%>
<body>
	<input type="hidden" id="login_userName" name="login_userName"
		value=<%=(String) session.getAttribute("login_userName")%> />
    <div style="display:none;">

        <!-- Start page content -->
        <div id="start-div">
            <div style="float:left;" ><img src="resources/images/layout-icon.gif" /></div>
            <div style="margin-left:100px;">
                <h2>欢迎使用企业信息平台后台管理系统!</h2>
                <p>后台管理系统包含了Ukey管理，用户管理，产品管理等诸多模块。</p>
                <p>从左边的菜单里选择一个功能试试看吧。</p>
                <p>如果您想获得帮助，请点击<a href="help.html" target="_blank">这里</a>。</p>
            </div>
        </div>
    </div>
</body>
</html>