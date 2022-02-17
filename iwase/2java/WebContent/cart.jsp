<%@ page import="searchPurchase.CartBean"%>
<%@ page import="acount.TaxBean"%>
<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="searchPurchase.SearchBooksDetalBean"%>
<%@ page import="searchPurchase.UserSessionBean"%>
<%@ page import="acount.LoginBean"%>
<%@ page import="java.text.NumberFormat"%>


<%
	UserSessionBean userSB = (UserSessionBean) session.getAttribute("userSB");
	System.out.println(userSB + "cartのuserSB");
	LoginBean loginInfo = userSB.getLoginInfo();
	Map<String, CartBean> mapCartBean = userSB.getMapCartBean();
	System.out.println(mapCartBean + "cartのmapCartBean");
	TaxBean taxBean = userSB.getTaxBean();
	double tax = taxBean.getTax();
	int userID = Integer.parseInt(loginInfo.getUser_id());
	String loginHed = userSB.getLoginHed();
	int stock = 0;
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
<%=loginHed%>

<main>


	<%
	if(mapCartBean != null){
		if (mapCartBean.size() != 0) {
			long sumPrice = 0;
			long sumPrice2 = 0;

			out.print("<div id='cart_main'>");
			out.print("<div id='cart_main2'>");


			for (CartBean cartBean : mapCartBean.values()) {

				SearchBooksDetalBean searchBDB = cartBean.getSearchBDB();
				//System.out.println(searchBDB + "cartのsearchBDB");
				int NumOfPur = cartBean.getNumOfPur();
				stock = Integer.parseInt(searchBDB.getStock());
				//out.print(searchBDB.getIsbn() + "<br>");


				//out.print(searchBDB.getInfo() + "<br>");
				//out.print(searchBDB.getStock() + "<br>");

				//out.print(searchBDB.getPublish_name() + "<br>");
				//out.print(searchBDB.getCategory_name() + "<br>");
				//out.print(searchBDB.getSales_date() + "<br>");

				out.print("<div class='itmes3'>");
				out.print("<div>");
				out.print("		<img src='img/" + searchBDB.getImage() + ".jpg' alt='' class='resultimg2'>");
				out.print("</div><div>");

				out.print("<p>" + searchBDB.getTitle() + "<br>" + searchBDB.getAuthor_name() + "</p>");

				int smallSumPrice = NumOfPur * Integer.parseInt(searchBDB.getPrice());
	%>


				<form action="CartChangeServlet" method="post">
					<input type="hidden" value="<%=searchBDB.getIsbn()%>" name="isbn">
					<select onchange="submit(this.form)" name="NumOfPur">
						<%
							for (int selecti = 0; selecti <= stock; selecti++) {
										if (selecti != NumOfPur) {
											out.println("<option value='" + selecti + "'>" + selecti + "</option>");
										} else {
											out.print("<option value='" + selecti + "' selected='selected'>" + selecti + "</option>");
										}

									}
						%>

					</select>冊
				</form>
				<form action="CartChangeServlet" method="post">
					<input type="submit" name="delete" value="消去"> <input
						type="hidden" value="<%=searchBDB.getIsbn()%>" name="isbn">
				</form>
	<%
				NumberFormat nfNum = NumberFormat.getNumberInstance();
				out.print("<p>" + NumOfPur + "冊×" + nfNum.format(Integer.parseInt(searchBDB.getPrice())) + "円=" + nfNum.format(smallSumPrice) + "</p>");
				sumPrice += smallSumPrice;

				out.print("</div></div>");

			}

			out.print("</div>");
			sumPrice2 = Math.round(sumPrice * tax);


	%>

			<hr>
			<p id="subtotal">合計金額<span>
			<% NumberFormat nfCur = NumberFormat.getCurrencyInstance();
			   out.print(nfCur.format(sumPrice2));
			%>
			</span>(本体<% out.print(nfCur.format(sumPrice)); %>)</p>
			<hr>


	<h4>購入者情報</h4>
	<form action="CartCheckServlet" method="post">

	<div id="cart_buy" id="tablebutton1">
		<table>
		<tr><td class="cart_td">UserID</td><td> <%=loginInfo.getUser_id()%> </td></tr>
		<tr><td class="cart_td">名前 </td><td> <%=loginInfo.getName()%> </td></tr>
		<tr><td class="cart_td">フリガナ </td><td> <%=loginInfo.getHurigana()%> </td></tr>
		<tr><td class="cart_td">生年月日 </td><td> <%=loginInfo.getBirthday()%> </td></tr>
		<tr><td class="cart_td">性別 </td><td> <%=loginInfo.getSex()%> </td></tr>
		<tr><td class="cart_td">tell </td><td> <%=loginInfo.getTel()%> </td></tr>
		<tr><td class="cart_td">email </td><td> <%=loginInfo.getEmail()%> </td></tr>
		<tr>
		<td class="cart_td">送り先</td>
		<td>
		<label>
		<input type="radio" name="address"
					value="<%=loginInfo.getAdPost()%>:<%=loginInfo.getAddress()%>"
					checked="checked"> <%=loginInfo.getAdPost()%>,address:<%=loginInfo.getAddress()%>
		</label><br>

				<%
					for (int posi = 0; posi < loginInfo.getSend_post().size(); posi++) {
				%>
		<label>
		<input type="radio" name="address"value="<%=loginInfo.getSend_post().get(posi)%>:<%=loginInfo.getSend_adress().get(posi)%>">
				<%
					out.print(loginInfo.getSend_post().get(posi) + ":" + loginInfo.getSend_adress().get(posi));
						out.print("</label><br>");
					}
				%>
		</td>
		</tr>
		<tr><td class="cart_td">決済方法</td><td> <label> <input type="radio" name="pay" value="cash"checked="checked">現金
				</label> <label> <input type="radio" name="pay" value="bank">銀行振込
				</label> <label> <input type="radio" name="pay" value="card">クレジットカード
				</label> </td></tr>

		</table>



				<div id="index_product" style='margin-top:0'>
				<%

				if(mapCartBean != null){
					out.print("<br><input type='submit' class='index_input' style='margin-top:0' value='購入確認へ'>");
				}
					%>

					<input type="hidden"name="cartCheck" value="cartCheck"> <input type="hidden"name="tax" value="<%=tax%>">
				</div>

			</form>


	</div>

	<%
		} else {
			out.print("<p>カートは空です</p>");
		}
		}else{
		out.print("<p>カートは空です</p>");
		}
	%>
		</div><!-- cart_buy -->
	</div><!-- cart_main -->

</main>
</body>
</html>