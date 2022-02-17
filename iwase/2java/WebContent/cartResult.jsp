<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String loginHed = (String)request.getAttribute("loginHed");
%>

<!DOCTYPE html>
<html lang="jp">
<head>
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/style2.css">
	<meta charset="UTF-8">
	<title>e-Intecs</title>
</head>
<body>
<%=loginHed %>
<main>
	<div id="index_product">
		<p>購入が完了しました。</p>
		<form action="IndexServlet">
		<input type="submit" class="index_input" value="Topに戻る">
	</form>
	</div>
</main>
</body>
</html>