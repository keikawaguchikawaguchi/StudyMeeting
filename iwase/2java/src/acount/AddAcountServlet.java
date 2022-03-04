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
 * Servlet implementation class AddAcountServlet
 */
public class AddAcountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddAcountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//直接来た場合
		String mess = "エラーが発生しました。<br>";
		request.setAttribute("mess", mess);
		String nextPage = "error.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//新規会員登録実行用サーブレット

		//ページ遷移用変数
		String nextPage = null;

		try {
			//入力されたデータを受取
			String name = request.getParameter("name");
			String hurigana = request.getParameter("hurigana");
			Date birthday = Date.valueOf(request.getParameter("birthday"));
			String sex = request.getParameter("sex");
			String tel = request.getParameter("tel");
			String email = request.getParameter("email");
			String adPost = request.getParameter("adPost");
			String address = request.getParameter("address");
			String password = request.getParameter("password");

			//ログイン用変数
			HttpSession session = request.getSession(false);
			CtmLoginAcountBeanDAO loginCheck;
			LoginBean loginInfo;

			if((boolean)request.getAttribute("timeout")){
				nextPage = "sessionTimeOut.jsp";
			}else {

				//メールとパスワードがすでに登録されていないかチェック
				loginCheck = new CtmLoginAcountBeanDAO();
				loginInfo = loginCheck.loginCheck(email, password);

				if(loginInfo == null) {
					//ユーザデータがなければ新規登録する。
					CtmLoginAcountBeanDAO addAcount = new CtmLoginAcountBeanDAO();
					boolean addAcountCheck = addAcount.addRegist(name,hurigana,birthday,sex,tel,email,adPost,address,password);
					System.out.println(addAcountCheck);
					//戻り値によってページ遷移
					if(addAcountCheck) {

						//登録成功
						//ログインしてトップページへ

						UserSessionBean userSB = (UserSessionBean)session.getAttribute("userSB");



							//Sessionの登録情報を更新するために再ログイン
							loginCheck = new CtmLoginAcountBeanDAO();
							loginInfo = loginCheck.loginCheck(email, password);
							userSB.setLoginInfo(loginInfo);

							userSB.setLoginHed(LoginCheckClass.loginOk(loginInfo.getName()));
							TaxBean taxBean = loginCheck.getTax();
							userSB.setTaxBean(taxBean);

							session.setAttribute("userSB", userSB);

						nextPage = "IndexServlet";

					}else {
						//登録失敗
						nextPage = "error.jsp";
					}

				}else {
					//登録済みなら警告
					String mess = "登録済みです。";
					request.setAttribute("mess", mess);
					nextPage = "regist.jsp";
				}
			}
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
