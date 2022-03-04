<%@page import="master.ProductSubBean"%>
<%@page import="master.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>

<%
	List<ProductBean> producList = (List<ProductBean>) request.getAttribute("productList");
	ProductSubBean pSB = (ProductSubBean) request.getAttribute("pSB");
	Map<String, String> authorMap = pSB.getAuthorMap();
	Map<String, String> categoryMap = pSB.getCategoryMap();
	Map<String, String> publishMap = pSB.getPublishMap();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/admin_style.css">
<title>e-Intecs管理画面</title>
</head>
<body>
	<h1>
		<a href="admin.jsp">管理者TOP</a>
	</h1>
	<h2>商品在庫変更</h2>


	<%
		for (ProductBean pb : producList) {
	%>
	<form action="DbchangeServlet" method="post">
		<input type="hidden" name="searchisbn" value="<%=pb.getIsbn()%>">
		<table>
			<tr>
				<th>書籍ID</th>
				<th>タイトル</th>
				<th>カテゴリー</th>
				<th>著者</th>
				<th>出版社</th>
				<th>価格</th>
				<th>在庫</th>
				<th>発売日</th>
				<th>書籍説明</th>
				<th>画像パス</th>
			</tr>
			<tr>
				<td><input type="text" name=isbn value="<%=pb.getIsbn()%>"></td>
				<td><input type="text" name=title value="<%=pb.getTitle()%>"></td>
				<td><select name="category">

						<%
							for (Map.Entry<String, String> entry : categoryMap.entrySet()) {

									if (entry.getKey().equals(pb.getCategoryID())) {
										out.print(
												"<option value='" + entry.getKey() + "' selected>" + entry.getValue() + "<opthion>");
									} else {
										out.print("<option value='" + entry.getKey() + "'>" + entry.getValue() + "<opthion>");
									}
								}
						%>

				</select></td>
				<td><select name="author">

						<%
							for (Map.Entry<String, String> entry : authorMap.entrySet()) {

									if (entry.getKey().equals(pb.getAuthorID())) {
										out.print(
												"<option value='" + entry.getKey() + "' selected>" + entry.getValue() + "<opthion>");
									} else {
										out.print("<option value='" + entry.getKey() + "'>" + entry.getValue() + "<opthion>");
									}
								}
						%>


				</select></td>
				<td><select name="publish">

						<%
							for (Map.Entry<String, String> entry : publishMap.entrySet()) {

									if (entry.getKey().equals(pb.getPublishID())) {
										out.print(
												"<option value='" + entry.getKey() + "' selected>" + entry.getValue() + "<opthion>");
									} else {
										out.print("<option value='" + entry.getKey() + "'>" + entry.getValue() + "<opthion>");
									}
								}
						%>


				</select></td>
				<td><input type="text" name=price value="<%=pb.getPrice()%>"></td>
				<td><input type="text" name=stock value="<%=pb.getStock()%>"></td>
				<td><input type="date" name=sales_date
					value="<%=pb.getSales_date()%>"></td>
				<td><textarea rows="" cols="" name="explanation"><%=pb.getInfo()%></textarea></td>
					<td><input type="text" name=image value="<%=pb.getImage()%>"></td>
				<td><input type="submit" name="btn" value="変更"></td>
				<td><input type="submit" name="btn" value="削除"></td>
			</tr>

			</form>
			<%
				}
			%>

			<form action="DbchangeServlet" method="post">
				<tr>
					<td>
					<input type="text" name=isbn value="">
					</td>
					<td><input type="text" name=title value=""></td>
					<td><select name="category">

							<%
								for (Map.Entry<String, String> entry : categoryMap.entrySet()) {

									out.print("<option value='" + entry.getKey() + "'>" + entry.getValue() + "<opthion>");

								}
							%>

					</select></td>
					<td><select name="author">

							<%
								for (Map.Entry<String, String> entry : authorMap.entrySet()) {

									out.print("<option value='" + entry.getKey() + "'>" + entry.getValue() + "<opthion>");

								}
							%>


					</select></td>
					<td><select name="publish">

							<%
								for (Map.Entry<String, String> entry : publishMap.entrySet()) {

									out.print("<option value='" + entry.getKey() + "'>" + entry.getValue() + "<opthion>");

								}
							%>


					</select></td>
					<td><input type="text" name=price value=""></td>
					<td><input type="text" name=stock value=""></td>
					<td><input type="date" name=sales_date value=""></td>
					<td><textarea rows="" cols="" name="explanation"></textarea></td>
					<td><input type="text" name=image value=""></td>
					<td><input type="submit" name="btn" value="追加"></td>

				</tr>
		</table>
	</form>

	<div id="admin_top">
		<a href="admin.jsp"><input type=button value="管理者TOPに戻る"></a>
	</div>
</body>
</html>