<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Itcast OA</title>
	<%@ include file="/WEB-INF/jsp/public/common.jspf"%>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.cookie.js"></script>
</head>

	<frameset rows="100,*,25" framespacing=0 border=0 frameborder="0">
		<frame noresize name="TopMenu" scrolling="no" src="${pageContext.request.contextPath}/indexAction_top.do">
		<frameset cols="180,*" id="resize">
			<frame noresize name="menu" scrolling="yes" src="${pageContext.request.contextPath}/indexAction_left.do">
			<frame noresize name="right" scrolling="yes" src="${pageContext.request.contextPath}/indexAction_right.do">
		</frameset>
		<frame noresize name="status_bar" scrolling="no" src="${pageContext.request.contextPath}/indexAction_bottom.do">
	</frameset>

	<noframes><body>
</body>
</noframes></html>



