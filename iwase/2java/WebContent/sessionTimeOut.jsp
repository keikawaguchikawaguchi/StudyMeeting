<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String loginHed = (String)session.getAttribute("loginHed");
%>

<!DOCTYPE html>
<html lang="jp">
<head>
	<link rel="stylesheet" href="css/style.css">
	<meta charset="UTF-8">
	<title>e-Intecs</title>
</head>
<body>
<%=loginHed %>
<main>
	session切れ
	<form action="IndexServlet">
	<input type="submit" value="Topに戻る">
	</form>
	<form action="LoginPageServlet">
	<input type="submit" value="ログイン">
	</form>
</main>
</body>
</html>