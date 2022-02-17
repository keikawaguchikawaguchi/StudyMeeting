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
	<h1><a href="admin.jsp">管理者TOP</a></h1>
	<h2>購入履歴</h2>
	<div class="history_div">
	<table>

		<tr>
			<th class="history_th">書籍</th>
			<th class="history_th">日付</th>
			<th class="history_th">購入者番号</th>
			<th class="history_th">購入者ID</th>
			<th class="history_th">商品名</th>
			<th class="history_th">個数</th>
			<th class="history_th">商品別小計</th>
			<th class="history_th">お届け先</th>
		</tr>

		<tr>
			<td>書籍ID</td>
			<td>1/1</td>
			<td>001</td>
			<td>A1</td>
			<td>book</td>
			<td>2</td>
			<td>1000</td>
			<td>愛知県</td>
		</tr>

		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>わかる本</td>
			<td>3</td>
			<td>3000</td>
			<td>愛知県</td>
		</tr>

		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td><strong>合計</strong></td>
			<td>4000</td>
			<td></td>
		</tr>

		<tr>
			<td>書籍ID</td>
			<td>1/2</td>
			<td>001</td>
			<td>A1</td>
			<td>book</td>
			<td>2</td>
			<td>1000</td>
			<td>愛知県</td>
		</tr>

		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>わかる本</td>
			<td>3</td>
			<td>3000</td>
			<td>愛知県</td>
		</tr>

		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td><strong>合計</strong></td>
			<td>4000</td>
			<td></td>
		</tr>

	</table>
</div>
<div id="admin_top">
	<a href="admin.jsp"><input type="button" value="管理者TOPに戻る"></a>
</div>

</body>
</html>