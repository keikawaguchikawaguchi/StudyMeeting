<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mess = (String)request.getAttribute("mess");
%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
エラーページ<br>
<%
if(mess != null){
	out.println(mess);
	}
%>
<br>
<a href="First">Topページへ</a>
</body>
</html>