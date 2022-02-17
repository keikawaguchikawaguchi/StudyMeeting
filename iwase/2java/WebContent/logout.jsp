<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String uuid = (String) request.getAttribute("uuid");
String loginHed = (String)request.getAttribute("loginHed");
%>

<!DOCTYPE html>
<html lang="jp">
<head>
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/style2.css">
	<meta charset="UTF-8">
	<title>e-Intecs</title>
</head>
<body>
<%=loginHed %>
<main>
	<div id="mypagemain">
		<form action="LoginSelectServlet" method="post">
			<input type="hidden"  name="log" value="logout">

			<input type="submit" class="index_input" value="ログアウト">
			<input type="hidden" name="uuid" value="<%=uuid%>">
		</form>
	</div>
</main>
</body>
</html>