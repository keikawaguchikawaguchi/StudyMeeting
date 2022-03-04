package acount;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CancelServlet
 */
public class CancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		CancelBeanDAO CancelBeanDAO = new CancelBeanDAO();

		//セッションを持ってくる

		HttpSession session = request.getSession(false);
		String nextPage = null;
		if((boolean)request.getAttribute("timeout")){
			nextPage = "sessionTimeOut.jsp";
		}else {


			String str =(String)request.getParameter("cancel");

			int pID = Integer.parseInt(str);

			CancelBeanDAO.getListByCan(pID);

			//どこへ遷移するか

			request.setAttribute("pID",pID);

			nextPage = "cancel.jsp";

			}


		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
