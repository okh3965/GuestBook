<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="<c:url value = "/gb"/>" method="POST">
		<input type="hidden" name="action" value="insert"/>		
	<table border=1 width=500>
		<tr>
			<td>이름</td><td><input type="text" name="name"></td>
			<td>비밀번호</td><td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
		</tr>
		<tr>
			<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
		</tr>
	</table>
	</form>
	<br/>

	<c:forEach items="${ list }" var="vo">
	<table width=510 border=1>
		<tr>
			<td>${ vo.no }</td>
			<td>${ vo.name }</td>
			<td>${ vo.regDate }</td>
			<td><a href="<c:url value = "/gb?a=deleteform"/>">삭제</a></td>
		</tr>
		<tr>
			<td colspan=4>${ vo.content }</td>
		</tr>
	</table>
        <br/>
	</c:forEach>
</body>
</html>