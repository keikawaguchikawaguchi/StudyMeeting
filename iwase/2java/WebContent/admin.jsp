<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<div id="admin_div">
		<form action="MasterSelectServlet" method="post">
			<input type="submit" name=btnProduct class=btnProduct value="商品管理">
			<input type="hidden" name="mstSelectNum" value="1">
		</form>
		<br>
		<form action="MsterSelectServlet" method="post">
			<input type="button" name=btnProduct_sub class=btnProduct_sub
				value="カテゴリー名、著者名、出版社名変更"> <input type="hidden"
				name="mstSelectNum" value="2">
		</form>
		<br>
		<form action="MsterSelectServlet" method="post">
			<input type="button" name=btnCustomer class=btnCustomer value="顧客">
			<input type="hidden" name="mstSelectNum" value="3">
		</form>
		<br>
		<form action="MsterSelectServlet" method="post">
			<input type="submit" name=btnManager class=btnManager value="管理者">
			<input type="hidden" name="mstSelectNum" value="4">
		</form>
		<br>
		<form action="MasterSelectServlet" method="post">
			<input type="submit" name=btnTax class=btnTax value="税率変更"> <input
				type="hidden" name="mstSelectNum" value="5">
		</form>
		<br>
		<form action="MsterSelectServlet" method="post">
			<input type="button" name=btnAdmin_history class=btnAdmin_history
				value="購入履歴"> <input type="hidden" name="mstSelectNum"
				value="6">
		</form>
	</div>
</body>
</html>