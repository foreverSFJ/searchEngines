<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>章节列表——看看没有广告的小说吧~~</title>
    <link href="view/css/bootstrap.min.css" rel="stylesheet">
    <script src="view/js/jquery.1.11.3.min.js"></script>
	<script src="view/js/bootstrap.min.js"></script>
	<script src="view/js/base64.js"></script>
</head>
<body>
	<div class="jumbotron" style="padding:5px; height: 40px;">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<a href="index.jsp">回到搜索页</a>
				</div>
			</div>
		</div>
	</div>
	<div class="container no-table-responsive">
		<table class="table table-striped table-bordered table-condensed table-hover">
			<thead>
				<tr>
					<th colspan="4" style="text-align:center;">章节列表</th>
				</tr>
			</thead>
			<tbody id="list">
				<c:forEach items="${chapterList}" var="chapter" varStatus="status">
					<c:if test="${(status.index+1) % 3 == 1}">
						<tr>
					</c:if>
					<td><a href="ChapterContenrServlet?url=${chapter.url }&baseUrl=${baseUrl}">${chapter.title }</a></td>
					<c:if test="${(status.index+1) % 3 == 0}">
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<!-- 这里可以按自喜好添加内容，如站点，版权，等等，还有小说评价，或者可以把聊天室加在这里~~~~一大堆，随便~~ -->
		<div>
			<div class="fixed-bottom" style="width:70px; margin-left: 10px;"> 
				<img style="display:block; margin-bottom: 20px; width:65px; height: 65px;" alt="微信：17371253695" src="view/images/weixin.jpg">
				<img style="display:block; margin-bottom: 20px; width:65px; height: 65px;" alt="QQ：2815462931" src="view/images/qq.jpg">
			</div>
		</div>
	</div>
</body>
</html>