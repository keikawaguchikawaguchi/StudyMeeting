package searchPurchase;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import acount.TaxBean;

/**
 * Servlet implementation class CartCheckServlet
 */
public class CartCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartCheckServlet() {
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
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		String nextPage = null;

		if((boolean)request.getAttribute("timeout")) {
			nextPage = "sessionTimeOut.jsp";
		}else {

			UserSessionBean userSB = (UserSessionBean)session.getAttribute("userSB");


			int userID = Integer.parseInt(userSB.getLoginInfo().getUser_id());
			String ad = request.getParameter("address");

			int index1 = ad.indexOf(":");
			String selectAdPost = ad.substring(0,index1);
			System.out.println(selectAdPost+"selectAdpost");	String selectAddress = ad.substring(index1 + 1);

			TaxBean taxBean = userSB.getTaxBean();
			double tax = taxBean.getTax();
			Map<String, CartBean> mapCartBean = userSB.getMapCartBean();
			List<String> cartCheck = new CartDBCheck().cartDBCheck(mapCartBean);
			if(cartCheck.size() > 0) {
				nextPage = "cartErro.jsp";
				request.setAttribute("cartCheck", cartCheck);
				for(String value: cartCheck) {
					mapCartBean.remove(value);
				}

			}else {
				boolean insertFlag = new CtmCartBeanDAO().insertPurchase(userID, selectAdPost, selectAddress, tax,mapCartBean);

				if(insertFlag) {
					nextPage = "cartResult.jsp";
					mapCartBean.clear();
					userSB.setMapCartBean(mapCartBean);

				}else {
					nextPage ="cart.jsp";
				}
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);

	}

}
