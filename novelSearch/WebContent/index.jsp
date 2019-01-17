<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>看看没有广告的小说吧~~</title>
	<link href="view/css/bootstrap.min.css" rel="stylesheet">
    <script src="view/js/jquery.1.11.3.min.js"></script>
	<script src="view/js/bootstrap.min.js"></script>
	<script src="view/js/base64.js"></script>
</head>
<script>
	var request = false;
	try {
		request = new XMLHttpRequest();
	} catch (trymicrosoft) {
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (othermicrosoft) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (failed) {
				request = false;
			}
		}
	}

	if (!request){
		alert("Error initializing XMLHttpRequest!");
	}

</script>
<body background="view/images/bg.jpg" style="font-family:仿宋">
	<div class="jumbotron" style="background-color:rgba(0,0,0,-0.5);">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h1>小说搜索引擎</h1>
					<form action="SimpleSearchServlet" method="post">
						<div class="input-group" style="margin-top: 80px;">
							<input type="text" name="keyword" id="keyword" class="form-control"  placeholder="请输入小说名...">
							<span>
								<input type="submit"  class="btn btn-default" value="小说搜索">
							</span>
						</div>
					</form>
				</div>
				<div style="height: 100px; width: 100%; text-align: center;">
					<a href="jiansuo.jsp" style="font-family: 楷体; color: #000000; text-decoration: none;">高级检索</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
