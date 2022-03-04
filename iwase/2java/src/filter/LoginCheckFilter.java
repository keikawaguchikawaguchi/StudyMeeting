package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import acount.LoginBean;
import searchPurchase.UserSessionBean;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
public class LoginCheckFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginCheckFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		if(session != null) {
			String loginHed =null;
			LoginBean loginInfo = null;
			LoginCheckClass loginCheckClass = new LoginCheckClass();
			boolean loginFlag = false;
			UserSessionBean userSB = (UserSessionBean)session.getAttribute("userSB");

			if(userSB == null) {
				loginFlag = false;
				userSB = new UserSessionBean();
				session.setAttribute("userSB", userSB);
				loginHed = loginCheckClass.loginNo();
			}else {
				loginInfo = userSB.getLoginInfo();
				System.out.println(loginInfo+"loginInfo");
				if(loginInfo != null) {

					loginHed = loginCheckClass.loginOk(loginInfo.getName());
					loginFlag = true;

				}else {

					loginHed = loginCheckClass.loginNo();
					loginFlag = false;
				}

			}
			request.setAttribute("timeout", false);
			request.setAttribute("loginFlag", loginFlag);
			request.setAttribute("loginHed", loginHed);
		}
		else {
			request.setAttribute("timeout", true);
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ


		System.out.println("アプリケーション初回起動");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */


}
