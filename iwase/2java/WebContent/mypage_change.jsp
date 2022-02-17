<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@page import="searchPurchase.SearchBooksDetalBean"%>

<%
	String loginHed = (String) request.getAttribute("loginHed");
	System.out.println(loginHed);
	List<List<SearchBooksDetalBean>> recomendBookList = (List<List<SearchBooksDetalBean>>) request
			.getAttribute("recomendBookList");
	System.out.println(recomendBookList + "recomendIndex");
	boolean loginFlag = (boolean) request.getAttribute("loginFlag");
%>
<!DOCTYPE html>
<html lang= "jp">
<head>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/style2.css">
	<meta charset = "UTF-8">
	<title>e-Intecs</title>
</head>
<body>
<%=loginHed %>
<main>


<%
//エラーメッセージ
String mess = (String)request.getAttribute("mess");
%>
<%
//入力されたデータ
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

if(name == null){
	name= "";
	hurigana= "";
	birthday= "";
	sex="";
	tel= "";
	email= "";
	adPost= "";
	address= "";
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/style2.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
if(mess != null){
	out.println(mess);
	}
%>
	<form action="UpdateAccountCheckServlet" method="post">
	<div class="mypage_change_div">
	<br><h1>会員情報変更</h1>
	<table>
		<tr><td class="mypage_change_td"> <input type="hidden" name="user_id" value="<%= user_id %>"> </td></tr>
		<tr><td class="mypage_change_td">  名前 </td><td> <input type="text" name="name" value="<%= name %>"> </td></tr>
		<tr><td class="mypage_change_td"> フリガナ </td><td> <input type="text" name="hurigana" value="<%= hurigana %>"> </td></tr>
		<tr><td class="mypage_change_td"> 生年月日 </td><td> <input type="date" name="birthday" value="<%= birthday %>"> </td></tr>
		<%
		//性別によって表示変更
		if(sex.equals("男")){
		out.println("<tr><td class=mypage_change_td> 性別 </td><td> <input type='radio' name='sex' value='男'checked>男");
		out.println("<input type='radio' name='sex' value='女'>女 </td></tr>");
		}else if(sex.equals("女")){
		out.println("<tr><td class=mypage_change_td> 性別 </td><td> <input type='radio' name='sex' value='男'>男");
		out.println("<input type='radio' name='sex' value='女' checked>女 </td></tr>");
		}
		%>
		<tr><td class="mypage_change_td"> 電話番号（例08011111111) </td><td> <input type="text" name="tel" value="<%= tel %>"> </td></tr>
		<tr><td class="mypage_change_td"> Eメール </td><td> <input type="email" name="email" value="<%= email %>"> </td> </tr>
		<tr><td class="mypage_change_td"> 郵便番号（例1234567）</td><td><input type="text" name="adPost" value="<%= adPost %>"> </td> </tr>
		<tr><td class="mypage_change_td"> 住所 </td><td> <input type="text" name="address" value="<%= address %>"> </td> </tr>
		<tr><td class="mypage_change_td"> 新しいパスワード </td><td> <input type="password" name="password"<%= password %>> </td> </tr>
		<tr><td class="mypage_change_td"> 新しいパスワード（確認）</td><td><input type="password" name="password2"<%= password %>> </td> </tr>
		</table>
		</div>
		<div class="mypage_change_input">
		<input type="submit"  class="mypage_change_submit" value="登録">
		</div>
	</form>

</main>
<footer></footer>
</body>
</html>