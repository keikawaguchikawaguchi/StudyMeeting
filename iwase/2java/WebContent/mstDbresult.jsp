<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	boolean mstDbFlag = (boolean) request.getAttribute("mstDbFlag");
%>>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		if (mstDbFlag) {
			out.print("更新完了");
		} else {
			out.print("更新失敗");
		}
	%>

	<form action="admin.jsp" method="post">
		<input type="submit" value="管理者Top画面にもdる">
	</form>

</body>
</html>