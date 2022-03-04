<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style_product_sub.css">
<link rel="stylesheet" type="text/css" href="css/admin_style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Intecs管理画面</title>
</head>
<body>
<h1><a href="admin.jsp">管理者TOP</a></h1>
	<h2>カテゴリー名、著者名、出版社名変更</h2>
	<form action="product_form">
		<div id="product_sub">
			<div id="category">
				<h4>カテゴリー名変更</h4>
				<table>
					<tr>
						<td>ID</td>
						<td><input type="text" name="title" value="カテゴリー名"></td>
						<td><input type="button" name="btnChange" value="変更"></td>
						<td><input type="button" name="btnDelete" value="削除" class="btnDelete"></td>
					</tr>

					<tr>
						<td>入力</td>
						<td><input type="text" name="title" value="カテゴリー名"></td>
						<td><input type="button" name="btnInsert" value="追加"></td>
					</tr>
				</table>
			</div>

			<div id="author">
				<h4>著者名変更</h4>
				<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name="title" value="著者名"></td>
					<td><input type="button" name="btnChange" value="変更"></td>
					<td><input type="button" name="btnDelete" value="削除" class="btnDelete"></td>
				</tr>
				<tr>
					<td>入力</td>
					<td><input type="text" name="title" value="著者名"></td>
					<td><input type="button" name="btnInsert" value="追加"></td>
				</tr>
				</table>
			</div>

			<div id="publish">
				<h4>出版社名変更</h4>
				<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name="title" value="出版社名"></td>
					<td><input type="button" name="btnChange" value="変更"></td>
					<td><input type="button" name="btnDelete" value="削除" class="btnDelete"></td>
				</tr>
				<tr>
					<td>入力</td>
					<td><input type="text" name="title" value="出版社名"></td>
					<td><input type="button" name="btnInsert" value="追加"></td>
				</tr>
				</table>
			</div>
		</div>
<div id="admin_top">
		<a href="admin.jsp"><input type=button value="管理者TOPに戻る"></a>
</div>
	</form>
</body>
</html>