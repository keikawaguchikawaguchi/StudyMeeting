<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String notLogin = (String) request.getAttribute("notLogin");
	String notLoginMess = (String) request.getAttribute("notLoginMess");
	String uuid = request.getParameter("uuid");
	System.out.println(uuid+"ログイン画面の受け取り");
%>

<%
String loginHed = (String)request.getAttribute("loginHed");
%>

<!DOCTYPE html>
<html lang="jp">
<head>
	<link rel="stylesheet" href="css/style.css">
	<meta charset="UTF-8">
	<title>e-Intecs</title>
</head>
<body>
<%=loginHed %>

<main>
	<div id="login_div">

<%
if(notLogin != null){
	out.print("<div class='login_errormsg'>" + notLogin + "</div>");
}

if(notLoginMess != null){
	out.print("<div class='login_errormsg'>" + notLoginMess + "</div>");
}
%>

		<form action="LoginSelectServlet" method="post" id="login_form">
			<div id="tablebutton1">
				<div>
					<p>メール</p>
					<p>パスワード</p>
				</div>
				<div>
					<p>
						<input type="hidden" name="log" value="login">
						<input type="email" name="email">
					</p>
					<p><input type="password"	name="password"><br></p>
				</div>
			</div>
			<div id="tablebutton2"><input type="submit" value="ログイン"></div>
		</form>
		<form action="regist.jsp" id="regist_form">
			<input type="submit" value="新規登録">
		</form>
	</div>
</main>

</body>
</html>