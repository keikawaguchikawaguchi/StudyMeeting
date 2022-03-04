package acount;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import filter.LoginCheckClass;
import searchPurchase.UserSessionBean;

/**
 * Servlet implementation class LoginSelectServlet
 */
public class LoginSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginSelectServlet() {
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

			UserSessionBean userSB = (UserSessionBean)session.getAttribute("userSB");
			String login_logoutCheck = request.getParameter("log");

			String loginHed = (String)request.getAttribute("loginHed");
			CtmLoginAcountBeanDAO loginCheck = new CtmLoginAcountBeanDAO();

			if(login_logoutCheck.equals("login")) {

				String email = request.getParameter("email");
				String password = request.getParameter("password");
				int index1 = email.indexOf("@");
				String master = email.substring(index1 + 1);


				if(master.equals("master")) {

					boolean masterLoginFlag = loginCheck.masterLogin(email, password);
					System.out.println(master+":::master");
					System.out.println(masterLoginFlag+"flag");
					if(masterLoginFlag) {
						nextPage ="AdminServlet";

					}else {
						nextPage = "LoginPageServlet";
						request.setAttribute("notLogin", "メールアドレスまたはパスワードが違います");

					}
				}else {

					LoginBean loginInfo = loginCheck.loginCheck(email, password);
					userSB.setLoginInfo(loginInfo);

					if(loginInfo != null) {

						userSB.setLoginHed(LoginCheckClass.loginOk(loginInfo.getName()));
						TaxBean taxBean = loginCheck.getTax();
						userSB.setTaxBean(taxBean);
						nextPage = "IndexServlet";
						session.setAttribute("userSB", userSB);

					}else {
						nextPage = "LoginPageServlet";
						request.setAttribute("notLogin", "メールアドレスまたはパスワードが違います");

					}
				}

			}else if(login_logoutCheck.equals("logout")) {

				session.invalidate();
				nextPage = "First";
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);


	}


}
