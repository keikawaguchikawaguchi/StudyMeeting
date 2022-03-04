<%@page import="master.SubProductBeanDAO"%>
<%@page import="searchPurchase.UserSessionBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="acount.HistoryBean"%>
<%@page import="acount.HistoryBooksBean"%>
<%@page import="java.text.SimpleDateFormat" %>

<%
String loginHed = (String)request.getAttribute("loginHed");
%>

<!DOCTYPE html>
<html lang="jp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/history.css">


<title>e-Intecs</title>
</head>
<body>
<%=loginHed%>
	<%
		List<HistoryBean> historyList =
		(List<HistoryBean>) request.getAttribute("historyList");
		System.out.println(historyList+":historyList");
		List<HistoryBooksBean> historyBBList =
				(List<HistoryBooksBean>) request.getAttribute("historyBBList");
		System.out.println(historyBBList+":historyBBList");
		boolean historyFlag = (boolean) request.getAttribute("historyFlag");
		System.out.println(historyFlag + "historyFlag");
		UserSessionBean userSB = (UserSessionBean) session.getAttribute("userSB");
		System.out.println(userSB + "userSB");
		System.out.println(loginHed + "loginHed");
	%>


	<main>
	<h1>購入履歴一覧 / キャンセル</h1>
	<div id="history_main">
	<hr>


	<%
		if (historyFlag) {



			for (HistoryBean value1: historyList) {
				double tax = value1.getTax();
				int subPrice = 0;
				int totalPrice = 0;

					out.print("<div class='history_table'>");
					out.print("<table style = center>");


					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

						out.print("<tr>");
							out.print("<th>購入日:</th><td>"+ sdf.format(value1.getPurchaseDate()) + "</td>");
						out.print("</tr>");

						out.print("<tr>");
							out.print("<th>注文番号:</th><td>" + value1.getPurchaseID() + "</td>");
						out.print("</tr>");

						out.print("<tr>");
							out.print("<th class = 'bar'>税率:</th><td class = 'bar'>" + tax + "</td>");
						out.print("</tr>");

							for (HistoryBooksBean value2 : historyBBList) {
								if (value1.getPurchaseID() == value2.getPurchaseID()) {
									subPrice = value2.getPrice() * value2.getPurchaseCount();
									totalPrice += subPrice;

									out.print("<tr>");
										out.print("<th>商品名:</th><td>" + value2.getTitle() + "</td>");
									out.print("</tr>");

									out.print("<tr>");
										out.print("<th>カテゴリー：</th><td>" + value2.getCategory() + "</td>");
									out.print("</tr>");

									out.print("<tr>");
										out.print("<th>著者:</th><td>" + value2.getAuthor() + "</td>");
									out.print("</tr>");

									out.print("<tr>");
										out.print("<th>価格:</th><td>" + value2.getPrice() + "円</td>");
									out.print("</tr>");

									out.print("<tr>");
										out.print("<th class = 'bar'>購入数:</th><td class = 'bar'>" + value2.getPurchaseCount() + "</td>");
									out.print("</tr>");

									out.print("<tr>");
										out.print("<th class = 'bar'>小計:</th><td class = 'bar'>"+ subPrice + "円</td>");
									out.print("</tr>");
								}

							}
							out.print("<tr>");
								out.print("<th>合計金額:</th><td><b>" + Math.round(totalPrice * tax)+"円</b></td>");
							out.print("</tr>");
					out.print("</table>");

						%>
						<form action="CancelServlet"method="post">
						<input type ="hidden" value = "<%=value1.getPurchaseID()%>" name = "cancel">
						<input type ="submit" value = "キャンセル">
						</form>
						<hr>
						</div>

						<%
			}
		} else {
			out.print("購入履歴はありません");
		}
	%>
	</div>
	</main>
<footer></footer>
</body>
</html>