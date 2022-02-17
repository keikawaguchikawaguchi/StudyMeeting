<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String loginHed = (String)request.getAttribute("loginHed");
%>


<!DOCTYPE html>
<html lang="jp">
<head>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/style2.css">
	<meta charset="UTF-8">
	<title>e-Intecs</title>
</head>

<body>
<%=loginHed %>
<main>
	<div id="mypagemain">
		<form action="HistoryServlet"method="post">
				<input type="submit" name="his" class="mypage_input" value="履歴一覧">
		</form>
		<br>
		<form action="UpdateAccountServlet"method="post">
				<input type="submit" class="mypage_input2" value="変更する">
		</form>
	</div>
</main>
<footer></footer>
</body>
</html>