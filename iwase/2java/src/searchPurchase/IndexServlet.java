package searchPurchase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import acount.CtmHistoryListBeanDAO;
import acount.HistoryBooksBean;
import acount.LoginBean;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		boolean loginFlag = (boolean)request.getAttribute("loginFlag");
		System.out.println(loginFlag +"loginFlag");
		String nextPage = null;




		if((boolean)request.getAttribute("timeout")) {
			nextPage = "sessionTimeOut.jsp";

		}else {

			if(loginFlag) {
				nextPage="index.jsp";
				UserSessionBean userSB = (UserSessionBean)session.getAttribute("userSB");
				System.out.println(userSB+"userSBINDex");
				LoginBean loginInfo = userSB.getLoginInfo();
				int userID =Integer.parseInt(loginInfo.getUser_id());
				System.out.println(userID+"userid");
				CtmHistoryListBeanDAO ctmHistoryListBeanDAO = new CtmHistoryListBeanDAO();
				CtmSearchBooksBeanDAO searchBBDAO = new CtmSearchBooksBeanDAO();

				List<HistoryBooksBean> historyBBList = ctmHistoryListBeanDAO.getHistoryBooksList(userID);
				Set<String> categorySet = new HashSet<String>();

				for(HistoryBooksBean value: historyBBList) {
					categorySet.add(value.getCategory());
				}
				System.out.println(categorySet+":categoryset");

				List<List<SearchBooksDetalBean>> recomendBookList =
						new ArrayList<List<SearchBooksDetalBean>>();

				for(String categoryName: categorySet) {

					List<SearchBooksDetalBean> recomendBooks =
							searchBBDAO.searchBooks("", "", categoryName);

					recomendBookList.add(recomendBooks);

				}
				System.out.println(recomendBookList+":recomendBookList");
				request.setAttribute("recomendBookList", recomendBookList);
			}
			nextPage="index.jsp";
		}
		request.setAttribute("loginFlag", loginFlag);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
