<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List"%>
<%

List<String> cartCheck = (List<String>)request.getAttribute("cartCheck");

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
for(String value: cartCheck){
	out.print(value+"<br>");

}

%>
上記の本は売り切れまたは削除されました。

<form action="IndexServlet">
<input type="submit" value="Topに戻る">
</form>

</body>
</html>