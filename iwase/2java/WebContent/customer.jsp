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
	<h2>新規会員登録</h2>
	<form action="customer_form">
		<table>
			<tr>
				<th class="customer_th">顧客</th>
				<th class="customer_th">名前</th>
				<th class="customer_th">フリガナ</th>
				<th class="customer_th">生年月日</th>
				<th class="customer_th">性別</th>
				<th class="customer_th">電話番号</th>
				<th class="customer_th">メールアドレス</th>
				<th class="customer_th">郵便番号</th>
				<th class="customer_th">住所</th>
				<th class="customer_th">パスワード</th>
			</tr>
			<tr>
				<td class="customer_id">顧客ID</td>
				<td><input type="text" name=name class="customer_id" value=""></td>
				<td><input type="text" name=hurigana class="customer_id" value=""></td>
				<td><input type="text" name=birthday class="customer_id" value=""></td>
				<td>
				<select name="sex">
				<option class="customer_sex">男</option>
				<option class="customer_sex">女</option>
				</select>
				</td>
				<td><input type="text" name=tel class="customer_id" value=""></td>
				<td><input type="text" name=mail class="customer_id2" value=""></td>
				<td><input type="text" name=adPost class="customer_id" value=""></td>
				<td><input type="text" name=address class="customer_id" value=""></td>
				<td><input type="text" name=password class="customer_id" value=""></td>
				<td><input type="button" name=btnChange class="customer_id" value="変更"></td>
				<td><input type="button" name=btnDelete class="customer_id" value="削除"></td>
			</tr>
		</table>

		<table>
			<tr>
				<td class="customer_id">顧客ID</td>
				<td><input type="text" name=name class="customer_id"></td>
				<td><input type="text" name=hurigana class="customer_id"></td>
				<td><input type="text" name=birthday class="customer_id"></td>
				<td>
				<select name="sex">
				<option class="customer_sex">男</option>
				<option class="customer_sex">女</option>
				</select>
				</td>
				<td><input type="text" name=tel class="customer_id"></td>
				<td><input type="text" name=mail class="customer_id2"></td>
				<td><input type="text" name=adPost class="customer_id"></td>
				<td><input type="text" name=address class="customer_id"></td>
				<td><input type="text" name=password class="customer_id"></td>
				<td><input type="button" name=btnInsert value="追加" class="customer_id"></td>
			</tr>


		</table>
		<div id="admin_top">
		<a href="admin.jsp"><input type="button" class="customer_top" value="管理者TOPに戻る"></a>
		</div>
	</form>
</body>
</html>