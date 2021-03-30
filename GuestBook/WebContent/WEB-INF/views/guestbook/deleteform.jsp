<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="<c:url value = "/gb?a=delete"/>" method="post">
	<input type='hidden' name="id" value="">
	<table>
		<tr>
			<td></td>
			<td><input type="password" name="password"></td>
			<td><input type="submit" value="확인"></td>
			<td><a href="<c:url value ="/gb"/>">메인으로 돌아가기</a></td>
		</tr>
	</table>
	</form>
</body>
</html>