<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
int pID = (int)request.getAttribute("pID");
%>

注文番号:　<%=pID%>　の
キャンセルが完了しました。

<form action="mypage.jsp"method="post">
<input type ="submit" value = "戻る">
</form>

</body>
</html>
