<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
 	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/detail/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/detail/style/css/index_1.css" />
<script type="text/javascript">
	function confirmDelete(deleteid){
		if(confirm("是否删除当前数据")){
			window.location="DeleteDeskServlet?deleteid="+deleteid;
		
		
		}
	
	}



</script>



</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/detail/style/images/title_arrow.gif"/> 餐桌列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


<!-- 过滤条件 -->
<div id="QueryArea">
				
	<form action="${pageContext.request.contextPath}/DeskServlet" method="get">
		<input type="hidden" name="method" value="search">
		<input type="text" name="dname" title="请输入餐桌名称">
		<input type="submit" value="搜索">
	</form>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>编号</td>
				<td>桌名</td>
				<td>状态</td>
				<td>预定时间</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
			<c:forEach var="temp" items="${requestScope.tableList.data}" >
			<tr class="TableDetail1">
				<td align="center">${pageScope.temp.DESKID}&nbsp;</td>
				<td align="center"> ${pageScope.temp.DNAME}&nbsp;</td>
				<td align="center">
				${pageScope.temp.DSTATE==0?'空闲':'预定'}
				</td>
				<td align="center">${pageScope.temp.DTIME}</td>
				<td>
					<a href="/wirelessplatform/board.html?method=update&id=1&isBook=0" class="FunctionButton">退桌</a>				
					<a href="javascript:confirmDelete(${pageScope.temp.DESKID})" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        </c:forEach>
		<%-- 分页代码 --%>
			<tr><td colspan="3">
			<a href="${pageContext.request.contextPath}/DeskServlet?curPage=1">首页</a>
			<a href="${pageContext.request.contextPath}/DeskServlet?curPage=${requestScope.tableList.previesPage}">上页 </a>
			<c:forEach var="i" begin="1" end="${requestScope.tableList.totalPage}" step="1">
			<a href="${pageContext.request.contextPath}/DeskServlet?curPage=${pageScope.i}">${pageScope.i}</a>
			</c:forEach>
			<a href="${pageContext.request.contextPath}/DeskServlet?curPage=${requestScope.tableList.nextPage}">下页 </a>
			<a href="${pageContext.request.contextPath}/DeskServlet?curPage=${requestScope.tableList.totalPage}">尾页</a>
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath}/detail/saveBoard.html">添加</a></div>
    </div> 
</div>
</body>
</html>
