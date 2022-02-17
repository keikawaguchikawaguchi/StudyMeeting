<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String loginHed = (String)request.getAttribute("loginHed");
	System.out.println(loginHed);
	boolean loginFlag = (boolean) request.getAttribute("loginFlag");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang = "jp">
<head>
<%
//エラーメッセージ
String mess = (String)request.getAttribute("mess");
%>
<%
//入力されたデータ
String name = (String)request.getAttribute("name");
String hurigana = (String)request.getAttribute("hurigana");
String birthday = (String)request.getAttribute("birthday");
String tel = (String)request.getAttribute("tel");
String email = (String)request.getAttribute("email");
String adPost = (String)request.getAttribute("adPost");
String address = (String)request.getAttribute("address");

if(name == null){
	name= "";
	hurigana= "";
	birthday= "";
	tel= "";
	email= "";
	adPost= "";
	address= "";
}
%>


<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" href="css/style2.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=loginHed %>
<main>
<%
if(mess != null){
	out.println(mess);
	}
%>

	<form action="RegistCheckServlet" method="post">
<div class="regist_button">
<div class="regist_div">
	  <br><h1>新規会員登録情報入力</h1>
	  <div id='tablebutton1'>
		<div class="regist_name">
	<p>名前</p>
	<p>フリガナ</p>
	<p>生年月日</p>
	<p>性別</p>
	<p>電話番号（例08011111111)</p>
	<p>Eメール</p>
	<p>郵便番号（例1234567）</p>
	<p>住所</p>
	<p>パスワード</p>
	<p>パスワード（確認）</p>
		</div>
			<div>
		<p><input type="text" name="name" class="regist_input" value="<%= name %>"></p>
		<p><input type="text" name="hurigana" class="regist_input" value="<%= hurigana %>"></p>
		<p><input type="date" name="birthday" class="regist_input" value="<%= birthday %>"></p>
		<p><input type="radio" name="sex" class="regist_input" value="男" checked>男
			<input type="radio" name="sex" class="regist_input" value="女">女</p>
		<p><input type="text" name="tel" class="regist_input" value="<%= tel %>"></p>
		<p><input type="email" name="email" class="regist_input" value="<%= email %>"></p>
		<p><input type="text" name="adPost" class="regist_input" value="<%= adPost %>"></p>
		<p><input type="text" name="address" class="regist_input" value="<%= address %>"></p>
		<p><input type="password" class="regist_input" name="password"></p>
		<p><input type="password" class="regist_input" name="password2"></p>
			</div>
	  </div>

</div>

<input type="submit" class="regist_submit" value="登録">
</div>
	</form>

</main>
<footer></footer>
</body>
</html>