package acount;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import filter.LoginCheckClass;
import searchPurchase.UserSessionBean;

/**
 * Servlet implementation class UpdateAccountDoServlet
 */
public class UpdateAccountDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateAccountDoServlet() {
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
		//顧客更新情報チェック後更新実行サーブレット
		request.setCharacterEncoding("UTF-8");

		String nextPage; //ページ遷移用
		String errorPassword = null; //確認用パス不適合用変数

		try {
			//ログイン確認
			HttpSession session = request.getSession(false);
			nextPage = null;
			if((boolean)request.getAttribute("timeout")) {
				nextPage = "sessionTimeOut.jsp";
			}else {

				//入力されたデータを受取
				int user_id = Integer.parseInt(request.getParameter("user_id"));
				String name = request.getParameter("name");
				String hurigana = request.getParameter("hurigana");
				Date birthday = Date.valueOf(request.getParameter("birthday"));
				String sex = request.getParameter("sex");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				String adPost = request.getParameter("adPost");
				String address = request.getParameter("address");
				String password = request.getParameter("password");
				String passcheck = request.getParameter("passcheck"); //確認用パスワード

				//確認パスワードとSessionパスワードを照合
				//ログイン情報をSessionから取り出す

				UserSessionBean userSB = (UserSessionBean)session.getAttribute("userSB");
				LoginBean loginInfo = userSB.getLoginInfo();

				//パスワード照合
				if(passcheck.equals(loginInfo.getPassword())) {
					//パスワードが一致したらデータベース更新
					CtmLoginAcountBeanDAO updateAcount = new CtmLoginAcountBeanDAO();
					boolean updateAcountCheck = updateAcount.updateAccount(user_id,name,hurigana,birthday,sex,tel,email,adPost,address,password);
					//戻り値によってページ遷移
					if(updateAcountCheck) {
						//登録成功
						//Sessionの登録情報を更新するために再ログイン
						CtmLoginAcountBeanDAO loginCheck = new CtmLoginAcountBeanDAO();
						loginInfo = loginCheck.loginCheck(email, password);
						userSB.setLoginInfo(loginInfo);

						userSB.setLoginHed(LoginCheckClass.loginOk(loginInfo.getName()));
						TaxBean taxBean = loginCheck.getTax();
						userSB.setTaxBean(taxBean);
						nextPage = "IndexServlet";
						session.setAttribute("userSB", userSB);

						nextPage = "First";
					}else {
						nextPage = "errer.jsp";
					}
					//フォワード
					RequestDispatcher rd = request.getRequestDispatcher(nextPage);
					rd.forward(request, response);

				}else {
					//パスワードが一致しなければ確認画面へ戻す
					nextPage = "mypage_check.jsp";
					errorPassword = "パスワードが違います。<br>";
					//jspに送るために型変更
					String userId = Integer.toString(user_id);
					String birthdayS= String.valueOf(birthday);
					//値をセット
					request.setAttribute("user_id", userId);
					request.setAttribute("name", name);
					request.setAttribute("hurigana", hurigana);
					request.setAttribute("birthday", birthdayS);
					request.setAttribute("sex", sex);
					request.setAttribute("tel", tel);
					request.setAttribute("email", email);
					request.setAttribute("adPost", adPost);
					request.setAttribute("address", address);
					request.setAttribute("password", password);
					request.setAttribute("errorPassword", errorPassword);
					//フォワード
					RequestDispatcher rd = request.getRequestDispatcher(nextPage);
					rd.forward(request, response);
				}
			}
			//フォワード
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);

		}catch(Exception ex) {
			//エラー発生時
			String mess = "エラーが発生しました。<br>";
			request.setAttribute("mess", mess);
			nextPage = "error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
		}
	}

}
