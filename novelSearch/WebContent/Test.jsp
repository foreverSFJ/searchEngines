<%@ page import="searchEngines.searchCon.SimpleSearchServlet"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.lucene.search.Hits"%>
<%@ page language="java" import="org.apache.lucene.document.Document"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML5>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
	<title>检索结果页</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<meta http-equiv="description" content="This is my page">
	<link href="view/css/bootstrap.min.css" rel="stylesheet">
    <script src="view/js/jquery.1.11.3.min.js"></script>
	<script src="view/js/bootstrap.min.js"></script>
	<script src="view/js/base64.js"></script>
</head>

<body style="font-family:仿宋">
	<%
		String flag = (String) request.getAttribute("flag");
	%>
	<%
		if (flag.equals("simpleSearch")) {
	%>
	<div class="jumbotron" style="height: 120px;">
		<div class="container">
			<div class="row">
				<div class="col-lg-12" style="margin-top: -50px;">
					<a href="index.jsp">回到搜索页</a>
					<form action="SimpleSearchServlet" method="post" class="input-group">
					<div class="input-group" >
						<input type="text" name="keyword" id="keyword" class="form-control" placeholder="请输入小说名..." onclick="this.value=''">
						<span class="input-group-btn">
							<button class="btn btn-default" type="submit" value="搜索">小说搜搜</button>
						</span>
					</div>
					</form>
				</div>
			</div>
		</div>
		<span style="float:left; color:red; margin-top: -18px;">检索词:
		 <%
	 	String word = (String) request.getAttribute("keyword");
	 		out.println(word);
	 %> <br />
	 <%			
	 			Hits hits = (Hits) request.getAttribute("hits");
	 			out.println("为您找到"+hits.length()+"条相关信息");
	 %>
		</span>
		<div style="height: 100px; width: 90%; text-align: center;">
			<a href="jiansuo.jsp" style="font-family: 楷体; color: #000000; text-decoration: none;">高级检索</a>
		</div>
	</div>
	<%
		} else if (flag.equals("juniorSearch")) {
	%>
		<div class="jumbotron" style="height: 120px;">
			<div class="container">
				<div class="row">
					<div class="col-lg-12" style="margin-top: -50px;">
						<a href="index.jsp">回到搜索页</a>
						<form action="SimpleSearchServlet" method="post" class="input-group">
						<div class="input-group" >
							<input type="text" name="keyword" id="keyword" class="form-control" placeholder="请输入小说名..." onclick="this.value=''">
							<span class="input-group-btn">
								<button class="btn btn-default" type="submit" value="搜索">小说搜搜</button>
							</span>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<span style="float:left; color:red; margin-top: -80px;">高级检索查询结果显示:<br>
 <%			
 			Hits hits = (Hits) request.getAttribute("hits");
 			out.print("------------:");
			out.println("为您找到" + hits.length() + "条相关信息");
 %>
 		</span>
	<%
		}
	%>
	<!-- 结果 -->
	<form action="ChapterContenrServlet" method="post">
	<div class="container no-table-responsive">
		<table class="table table-striped table-bordered table-condensed table-hover">
			<thead>
				<tr style="text-align:center;">
					<th style="width:45px">#</th>
					<td style="width:200px">书名</td>
					<td style="width:150px">作者</td>
					<td style="width:400px">最新章节</td>
					<td style="width:85px">状态</td>
					<td style="width:100px">平台</td>
				</tr>
			</thead>
			<tbody id="list">
				<%
					Hits hits = (Hits) request.getAttribute("hits");
					Document doc = null;
					for (int i = 0; i < hits.length(); i++) {
						doc = hits.doc(i);
						String index = i+1+"";
						String name = doc.get("name");
						String url = doc.get("url");
						String author = doc.get("author");
						String lastUpdateChapter = doc.get("lastUpdateChapter");
						String status = doc.get("status");
						String platformId = doc.get("platformId");
						
				%>
				<tr>
					<td><%=index%></td>
					<td><a href="ChapterListServlet?url=<%=url%>" ><%=name%></a></td>
					<td><%=author%></td>
					<td><%=lastUpdateChapter%></td>
					<td><%=status%></td>
					<td><%=platformId%></td>
				</tr>
				<%
					}
				%>
		</tbody>
	</table>
 </div>
</form>
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
