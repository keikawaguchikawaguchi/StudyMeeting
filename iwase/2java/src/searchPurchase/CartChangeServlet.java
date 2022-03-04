package searchPurchase;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CartChangeServlet
 */
public class CartChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartChangeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		String nextPage = null;

		if(session == null) {
			nextPage = "sessionTimeOut.jsp";
		}else {
			nextPage="cart.jsp";


			String isbn =request.getParameter("isbn");
			UserSessionBean userSB = (UserSessionBean)session.getAttribute("userSB");
			Map<String, CartBean> mapCartBean = userSB.getMapCartBean();
			CartBean cartBean = mapCartBean.get(isbn);
			String deleteFlag = request.getParameter("delete");
			System.out.println(deleteFlag);

			if(deleteFlag == null) {

				int numOfPur = Integer.parseInt(request.getParameter("NumOfPur"));
				cartBean.setNumOfPur(numOfPur);


			}else {
				mapCartBean.remove(isbn);
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);


	}

}
