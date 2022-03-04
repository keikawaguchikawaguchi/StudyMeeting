<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/admin_style.css">
<title>Insert title here</title>
</head>
<body>
<h1><a href="admin.jsp">管理者TOP</a></h1>
	<h2 class="manager_h2">管理者登録</h2>
<form action="manager_form">
		<table>
			<tr>
				<th class="manager_th">管理者</th>
				<th class="manager_th">名前</th>
				<th class="manager_th">フリガナ</th>
				<th class="manager_th">生年月日</th>
				<th class="manager_th">性別</th>
				<th class="manager_th">電話番号</th>
				<th class="manager_th">メールアドレス</th>
				<th class="manager_th">郵便番号</th>
				<th class="manager_th">住所</th>
				<th class="manager_th">パスワード</th>
			</tr>
			<tr>
				<td class="manager_id">管理者ID</td>
				<td><input type="text" name=name class="manager_id" value=""></td>
				<td><input type="text" name=hurigana class="manager_id" value=""></td>
				<td><input type="text" name=birthday class="manager_id" value=""></td>
				<td>
				<select name="sex">
				<option class="manager_sex">男</option>
				<option class="manager_sex">女</option>
				</select>
				</td>
				<td><input type="text" name=tel class="manager_id" value=""></td>
				<td><input type="text" name=mail class="manager_id2" value=""></td>
				<td><input type="text" name=adPost class="manager_id" value=""></td>
				<td><input type="text" name=address class="manager_id" value=""></td>
				<td><input type="text" name=password class="manager_id" value=""></td>
				<td><input type="button" name=btnChange class="manager_id" value="変更"></td>
				<td><input type="button" name=btnDelete class="manager_id" value="削除"></td>
			</tr>
		</table>

		<table>
			<tr>
				<td class="manager_id">管理者ID</td>
				<td><input type="text" name=name class="manager_id"></td>
				<td><input type="text" name=hurigana class="manager_id"></td>
				<td><input type="text" name=birthday class="manager_id"></td>
				<td>
				<select name="sex">
				<option class="manager_sex">男</option>
				<option class="manager_sex">女</option>
				</select>
				</td>
				<td><input type="text" name=tel class="manager_id"></td>
				<td><input type="text" name=mail class="manager_id2"></td>
				<td><input type="text" name=adPost class="manager_id"></td>
				<td><input type="text" name=address class="manager_id"></td>
				<td><input type="text" name=password class="manager_id"></td>
				<td><input type="button" name=btnInsert class="manager_id" value="追加"></td>
			</tr>


		</table>

			<div id="admin_top">
				<a href="admin.jsp"><input type=button class="manager_top" value="管理者TOPに戻る"></a>
			</div>
</form>
</body>
</html>