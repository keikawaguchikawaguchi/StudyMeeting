package acount;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import searchPurchase.UserSessionBean;

/**
 * Servlet implementation class HistoryServlet
 */
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);


		CtmHistoryListBeanDAO CtmHistoryLBDAO = new CtmHistoryListBeanDAO();


		String nextPage = null;

		if((boolean)request.getAttribute("timeout")) {
			nextPage = "sessionTimeOut.jsp";

		}else {

			//セッションの中のクラスLoginBeanを呼び出しloginInfoに入れる
			//☆変える

			UserSessionBean userSB = (UserSessionBean)session.getAttribute("userSB");
			LoginBean loginInfo = userSB.getLoginInfo();
			int userID =Integer.parseInt(loginInfo.getUser_id());

			//LoginInfoからuser_idを取り出す

			List<HistoryBean> historyList = CtmHistoryLBDAO.getHistoryList(userID);
			List<HistoryBooksBean> historyBBList = CtmHistoryLBDAO.getHistoryBooksList(userID);

			System.out.println(historyList);


			//どこへ遷移するか


			if(historyList.size()!=0){
				request.setAttribute("historyBBList", historyBBList);
				request.setAttribute("historyList", historyList);
				request.setAttribute("historyFlag", true);
			} else {

				request.setAttribute("historyFlag", false);
			}
			nextPage = "mypageHistory.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
