<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String user_id = (String)request.getAttribute("user_id");
String name = (String)request.getAttribute("name");
String hurigana = (String)request.getAttribute("hurigana");
String birthday = (String)request.getAttribute("birthday");
String sex = (String)request.getAttribute("sex");
String tel = (String)request.getAttribute("tel");
String email = (String)request.getAttribute("email");
String adPost = (String)request.getAttribute("adPost");
String address = (String)request.getAttribute("address");
String password = (String)request.getAttribute("password");
String passmess = (String)request.getAttribute("passmess");
String errorPassword = (String)request.getAttribute("errorPassword");

if(user_id == null){
	user_id = "";
	name = "";
	hurigana = "";
	birthday = "";
	sex = "";
	tel = "";
	email = "";
	adPost = "";
	address = "";
	password = "";
}
if(passmess == null){
	passmess = "";
}
if(errorPassword == null){
	errorPassword = "";
}
%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>会員情報更新確認</h2>

	<%
	if(name == null){
		out.println("入力エラーです。");
	}else{
	%>
	名前：<%= name %><br>
	フリガナ：<%= hurigana %><br>
	生年月日：<%= birthday %><br>
	性別：<%= sex %><br>
	電話番号<%= tel %><br>
	Eメール：<%= email %><br>
	郵便番号：<%= adPost %><br>
	住所：<%= address %><br>
	<%= passmess %><br>
	<br>

	<form action="UpdateAccountDoServlet" method="post">
	<input type="hidden" name="user_id" value="<%= user_id %>">
	<input type="hidden" name="name" value="<%= name %>">
	<input type="hidden" name="hurigana" value="<%= hurigana %>">
	<input type="hidden" name="birthday" value="<%= birthday %>">
	<input type="hidden" name="sex" value="<%= sex %>">
	<input type="hidden" name="tel" value="<%= tel %>">
	<input type="hidden" name="email" value="<%= email %>">
	<input type="hidden" name="adPost" value="<%= adPost %>">
	<input type="hidden" name="address" value="<%= address %>">
	<input type="hidden" name="password" value="<%= password %>">

	上記の内容で登録します。<br>

	確認のため、現在のパスワードを入力してください。<br>
	<input type="password" name="passcheck"><br>
	<%= errorPassword %>
	<input type="button" onclick="history.back()" value="戻る">
	<input type="submit" value="送信">
	<%
	}%>

	</form>


</body>
</html>