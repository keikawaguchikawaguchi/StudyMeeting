<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	double tax = (double) request.getAttribute("tax");
%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/admin_style.css">
<title>e-Intecs管理画面</title>
</head>
<body>
	<h1>
		<a href="admin.jsp">管理者TOP</a>
	</h1>
	<h2 class="tax_h2">税率変更</h2>
	<div class="admin_tax">
		<form action="TaxChangeServlet" method="post">
			<input type="text" name=tax value="<%=tax %>"> <input type="submit"
				value="変更"><br>
		</form>
	</div>
	<div id="admin_top">
		<a href="admin.jsp"><input type="button" class="tax_button"
			value="管理者TOPに戻る"></a>
	</div>
</body>
</html>