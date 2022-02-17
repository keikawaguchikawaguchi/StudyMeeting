package searchPurchase;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import acount.LoginBean;

/**
 * Servlet implementation class CartSelectServlet
 */
public class CartSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartSelectServlet() {
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
		HttpSession session  = request.getSession(false);

		String nextPage = null;

		if((boolean)request.getAttribute("timeout")) {
			nextPage = "sessionTimeOut.jsp";
		}else {


			UserSessionBean  userSB = (UserSessionBean)session.getAttribute("userSB");
			System.out.println(userSB);
			String isbn = request.getParameter("isbn");
			System.out.println(isbn);
			boolean loginFlag = (boolean)request.getAttribute("loginFlag");

			System.out.println(loginFlag + "loginFlag");

			CtmBooksBeanDAO booksBeanDAO= new CtmBooksBeanDAO();
			IntoCart intoCart = new IntoCart();
			if(loginFlag) {

				LoginBean loginInfo = userSB.getLoginInfo();

				String userID = loginInfo.getUser_id();
				int userIDI = Integer.parseInt(userID);

				int NumOfPur = Integer.parseInt(request.getParameter("NumOfPur"));


				SearchBooksDetalBean searchBDB = booksBeanDAO.getSearchBDB(isbn);
				Map<String, CartBean> mapCartBean = userSB.getMapCartBean();

				if(userSB.getMapCartBean()== null) {
					Map<String,CartBean> firstMapCartBean = intoCart.firstCartInto(NumOfPur, searchBDB, isbn);

					System.out.println("初回カート追加");

					mapCartBean = firstMapCartBean;
					userSB.setMapCartBean(mapCartBean);
				}else  {


					CartBean cartBean = mapCartBean.get(isbn);

					if(cartBean == null) {

						mapCartBean.put(isbn,intoCart.newItemCartInto(NumOfPur, searchBDB));

						System.out.println("二回目ダブりなし");

					}else if(cartBean != null) {

						mapCartBean.put(isbn,intoCart.secondItemCartInto( NumOfPur,cartBean));

						System.out.println("二回目だぶり");
					}

				}
				userSB.setMapCartBean(mapCartBean);
				session.setAttribute("userSB", userSB);
				nextPage = "cart.jsp";

			}else {
				nextPage = "login.jsp";
				request.setAttribute("notLoginMess", "カートに追加するにはログインをしてください。");
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
