<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>高级检索</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="view/css/bootstrap.min.css" rel="stylesheet">
    <script src="view/js/jquery.1.11.3.min.js"></script>
	<script src="view/js/bootstrap.min.js"></script>
	<script src="view/js/base64.js"></script>
</head>

<body background="view/images/bg.jpg" style="font-family:仿宋">
	<center style="margin-top: 80px;">
		<h2>高级检索</h2>
		<form action="JuniorSearchServlet" method="post" style="font-style: normal; font-size: 16px; font-weight: 500;" >
			<h3 style="font-weight: bold;">输入内容检索</h3>
			<select id="content1" name="content1" style="width: 100px; margin-left: 125px;">
				<option value='1' selected=''>标题</option>
				<option value='2'> 作者</option>
			</select> 
			简单查询&nbsp;&nbsp;&nbsp;<input type="text" id="txt1" name="txt1" /> 
			<select id="boolean2" name="boolean2" style="width: 100px; ">
				<option value='1' selected=''>并含</option>
				<option value='2'>或含</option>
				<option value='3'>不含</option>
			</select> 
			<input type="text" id="txt2" name="txt2"/> 
			<select id="fuzzy1" name="fuzzy1" style="width: 100px;">
				<option value='1' selected=''>精确</option>
				<option value='2'>模糊</option>
			</select><br /> &nbsp; <br /> &nbsp; 
			<select id="boolean3" name="boolean3" style="width: 100px; ">
				<option value='1' selected=''>并含</option>
				<option value='2'>或含</option>
				<option value='3'>不含</option>
			</select>&nbsp; <select id="content2" name="content2" style="width: 100px;">
				<option value='1' selected=''>标题</option>
				<option value='2'>作者</option>
			</select>
			通配符查询 <input type="text" id="txt3" name="txt3"  /> 
			<select id="boolean4" name="boolean4" style="width: 100px; ">
				<option value='1' selected=''>并含</option>
				<option value='2'>或含</option>
				<option value='3'>不含</option>
			</select> 
			<input type="text" id="txt4" name="txt4"  /> 
			<select id="fuzzy2" name="fuzzy2" style="width: 100px;">
				<option value='1' selected=''>精确</option>
				<option value='2'>模糊</option>
			</select>

			<h3 style="font-weight: bold;">输入检索控制条件</h3>
			&nbsp; 是否完结：<select id="fuzzy2" name="fuzzy2" style="width: 100px; ">
			 	<option value='1' selected=''>已完结</option>
				<option value='2'>连载中</option>
			</select>
			<p style="margin-top: 10px;">
				<input type="submit" value="搜索" style="height: 30px; width: 80px;">&nbsp;&nbsp;
			</p>
		</form>
	</center>
</body>
</html>
