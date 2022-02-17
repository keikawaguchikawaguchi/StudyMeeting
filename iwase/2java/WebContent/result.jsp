<%@page import="searchPurchase.SearchBooksDetalBean"%>
<%@page import="searchPurchase.UserSessionBean"%>
<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.text.NumberFormat"%>

<%
	boolean flag = (boolean) request.getAttribute("searchResultFlag");
	List<SearchBooksDetalBean> list = (List<SearchBooksDetalBean>)request.getAttribute("searchBDBList");
	request.setAttribute("searchBDBList",list);
	System.out.println(list+"resulutのリスト");
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
<div id="result_main2">
		<p>
		<% if(flag) {
				out.print(list.size() + "件、見つかりました。");
			} else {
				out.print("検索項目はありません。");
			}
		%>
		</p>
		<div id="resultmain">

<%
		if (flag) {
			for (int i = 0; i < list.size(); i++) {
				out.print("<div class='itmes'>");

				out.print("<form action = 'DetailServlet' method='get' class='items_form'>");
				out.print("<input type='hidden' name='booksListNum' value='" + i + "'>");
				out.print("<button type='' style='background-color: #FFF;'>");
				/*
				out.print(list.get(i).getIsbn() + "\t");
				out.print(list.get(i).getTitle() + "\t");
				out.print(list.get(i).getAuthor_name() + "\t");
				out.print(list.get(i).getPrice() + "\t");
				out.print(list.get(i).getInfo() + "\t");
				out.print(list.get(i).getStock() + "\t");
				out.print(list.get(i).getImage() + "\t");
				out.print(list.get(i).getPublish_name() + "\t");
				out.print(list.get(i).getCategory_name() + "\t");
				out.print(list.get(i).getSales_date());
				*/

				out.print("		<img src='img/" + list.get(i).getImage() + ".jpg' alt='' class='resultimg'>");
				out.print("</button>");
				out.print("<input type='hidden' name='isbn' value='" + list.get(i).getIsbn() + "'>");
				out.print("</form>");

				out.print("<form action = 'CartSelectServlet' method='post'>");
				out.print("<input type='hidden' name='isbn' value='" + list.get(i).getIsbn() + "'>");
				out.print("<input type='number' name='NumOfPur'value='1' min='1' max='" + list.get(i).getStock()
						+ "'>");
				out.print("<input type='submit' value='追加'>");
				out.print("</form>");

				NumberFormat nfCur = NumberFormat.getCurrencyInstance();
				out.print("	<p><span>" + nfCur.format(Integer.parseInt(list.get(i).getPrice())) + "</span><br>");
				out.print(list.get(i).getTitle() + "</p>");

				out.print("</div>");
			}
		} else {
			//out.print("検索にヒットしませんでした");
		}
	%>
		</div></div>
</main>
<footer></footer>
</body>
</html>