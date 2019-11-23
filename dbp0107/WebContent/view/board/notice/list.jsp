<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@include file = "/view/department/main.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>공지사항</title>
</head>
<body link="black" vlink="black" alink="black">
<table align = "center" width = "70%" class="list">
	<tr>
		<td width = "100" align = "center" height = "22">번호</td>
		<td width = "400" align = "center" height = "22">제목</td>
		<td width = "200" align = "center" height = "22">작성일</td>
		<td width = "200" align = "center" height = "22">작성자</td>
	</tr>
	<c:forEach var="board" items="${boardList}">
		<tr>
			<td width = "100" align = "center" height = "22">${board.notice_board_no}</td>
			<td width = "400" align = "center" height = "22">
				<a class = "list" href="<c:url value='/view/board/notice/detail'>
						   <c:param name="notice_board_no" value='${board.notice_board_no}'/>
						   <c:param name="department_no" value='${board.department_no}'/>
				 		 </c:url>">
				${board.title}</a>
			</td>
			<td width = "200" align = "center" height = "22">${board.createtime}</td>
			<td width = "200" align = "center" height = "22">${board.customer_name}</td>
		</tr>
	</c:forEach> 
</table>
<br>
<div style="text-align:center" class="list">
	<a href="<c:url value='/view/testMain' />">목록</a>
</div>
</body>
</html>


