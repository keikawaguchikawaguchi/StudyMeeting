<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>

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
	<div id="index_main">
		<%
		if (loginFlag) {
			if (recomendBookList.size() > 0) {

				out.print("<h2 id='daimei'>おすすめの本</h2>");

				out.print("<div id='resultmain2'>");
				for (List<SearchBooksDetalBean> searchBDBList : recomendBookList) {
					for (SearchBooksDetalBean searchBDB : searchBDBList) {

						out.print("<div class='itmes2'>");
						out.println(searchBDB.getPublish_name() + "<br>");
						out.print(searchBDB.getCategory_name() + "<br>");
						out.print("<form action = 'DetailServlet' method='get' class='items_form'>");
						out.print("<button type='' style='background-color: #FFF;'>");
						out.print("		<img src='img/" + searchBDB.getImage() + ".jpg' alt='' class='resultimg2'>");
						out.print("</button>");
						out.print("<input type='hidden' name='isbn' value='" + searchBDB.getIsbn() + "'>");
						out.print("</form>");


/*
						out.println(searchBDB.getPublish_name() + "<br>");
						out.print(searchBDB.getCategory_name() + "<br>");
						out.print("		<img src='img/" + searchBDB.getImage() + ".jpg' alt='' class='resultimg2'>");

*/
						out.print("</div>");
					}
				}
				out.print("</div>");
			}
		}
	%>

	</div>
</main>
<footer></footer>
</body>
</html>