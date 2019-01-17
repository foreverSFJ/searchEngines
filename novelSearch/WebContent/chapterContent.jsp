<%@ page import="searchEngines.searchCon.ChapterContenrServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${chapter.title }-章节内容-看看没有广告的小说吧~~</title>
    <link href="view/css/bootstrap.min.css" rel="stylesheet">
    <script src="view/js/jquery.1.11.3.min.js"></script>
	<script src="view/js/bootstrap.min.js"></script>
	<script src="view/js/base64.js"></script>
	<style type="text/css">
    	body {
    		background-color:#E5E4DB;
    	}
    	.content {
    		background-color:#F6F4EC;
    		color:#333;
    		padding:20px;
    		border-radius:5px;
    		-webkit-border-radiu:5px;
    	}
    	.jumbotron {
    		padding-top:10px;
    		padding-bottom:10px;
    		background-color:#F5F5F5;
    	}
    </style>
    <script type="text/javascript">
		window.onload = function() {
			var btnSub = document.getElementById("btnSub");
			var btnAdd = document.getElementById("btnAdd");
			var btnRed = document.getElementById("btnRed");
			var btnYellow = document.getElementById("btnYellow");
			var chapterContent = document.getElementById("chapterContent");
			var size = 20 ; 
			btnSub.onclick = function() {
				if(size >= 8) { //设置最小字体为8px
					size -= 2; //每次减2px
					chapterContent.style.fontSize = size + 'px';//+'px'为了浏览器兼容
				}
			}
			btnAdd.onclick = function() {
				if(size <= 40) { //设置最大字体是40px
					size += 2; //每次点击按钮字体增加2
					chapterContent.style.fontSize = size + 'px';
				}
			}
		}
	</script>
  </head>
<body>
	<div class="jumbotron">
		<div class="container" style="height:25px;">
			<div class="row" style="width:250px; float: left;">
				<div class="col-lg-12">
					<a href="./">回到搜索页</a>
				</div>
			</div>
			<div style="float: right;">
				<input id="btnAdd" class="btn-default" type="button" value="字体变大" />
				<input id="btnSub" class="btn-default" type="button" value="字体变小" />
			</div>
		</div>
	</div>
	<div class="container">
		<h3 style="text-align:center;">${chapter.title}</h3>
		<!-- 章节内容区域 -->
		<div class="content">
			<!-- 章节内容 -->
			<div id="chapterContent" style="font-size: 20px;">${chapter.content}</div>
			<!-- 前一章 章节列表 后一章 链接区域 -->
			<div style="text-align:center;">
				<a href="ChapterContenrServlet?url=${chapter.prev }&baseUrl=${baseUrl}">前一章</a>
				<a href="ChapterListServlet?url=${baseUrl}">章节列表</a>
				<a href="ChapterContenrServlet?url=${chapter.next }&baseUrl=${baseUrl}">后一章</a>
			</div>
		</div>
	</div>
	<div>
		<!-- 这里可以按自喜好添加内容，如站点，版权，等等，还有小说评价，或者可以把对章节的评论实时聊天室加在这里~~~~一大堆，随便~~ -->
		<div>
			<div class="fixed-bottom" style="width:70px; margin-left: 10px;"> 
				<img style="display:block; margin-bottom: 20px; width:65px; height: 65px;" alt="微信：17371253695" src="view/images/weixin.jpg">
				<img style="display:block; margin-bottom: 20px; width:65px; height: 65px;" alt="QQ：2815462931" src="view/images/qq.jpg">
			</div>
		</div>
	</div>
</body>
</html>