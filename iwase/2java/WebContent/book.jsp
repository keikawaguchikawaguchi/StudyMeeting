
<%@ page language="java" contentType="text/html; charset = UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="searchPurchase.SearchBooksDetalBean"%>
<%@ page import="searchPurchase.UserSessionBean"%>



<%
SearchBooksDetalBean searchBDB = (SearchBooksDetalBean)request.getAttribute("searchBDB");
String loginHed = (String)request.getAttribute("loginHed");
%>

<!DOCTYPE html>
<html lang = "jp">
<head>
	<link rel="stylesheet" href="css/style.css">
	<meta charset="UTF-8">
	<title>e-Intecs</title>
</head>
<body>
<%=loginHed %>

<main>
	<div id="bookmain2">
		<h2 id="subject"><% out.print(searchBDB.getTitle()); %></h2>
		<div id="bookmain">
			<img src="img/<% out.print(searchBDB.getImage()); %>.jpg" alt="" class="bookimg">
			<div id='tablebutton1'>
				<div id="sentence">
					<p>カテゴリー</p>
					<p>著者名</p>
					<p>価格</p>
					<p>在庫</p>
					<p>出版社</p>
					<p>発売日</p>
					<p>Isbn</p>
					<p>書籍説明</p>
				</div>
				<div>
					<p><% out.print(searchBDB.getCategory_name()); %></p>
					<p><% out.print(searchBDB.getAuthor_name()); %></p>
					<p><% out.print(searchBDB.getPrice()); %></p>
					<p><% out.print(searchBDB.getStock()); %></p>
					<p><% out.print(searchBDB.getPublish_name()); %></p>
					<p><% out.print(searchBDB.getSales_date()); %></p>
					<p><% out.print(searchBDB.getIsbn()); %></p>
					<p><% out.print(searchBDB.getInfo()); %></p>
				</div>
			</div>

			<form action="CartSelectServlet" method="post">
				<input type="hidden" name="isbn" value="<%=searchBDB.getIsbn()%>">
				<input type="number" name="NumOfPur" min="1" max="<%=searchBDB.getStock() %>" value='1'>
				<input type="submit" value="追加">
			</form>

		</div>
	</div>
</main>
</body>
</html>