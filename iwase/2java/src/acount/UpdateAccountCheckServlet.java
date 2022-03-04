package acount;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import searchPurchase.UserSessionBean;

/**
 * Servlet implementation class UpdateAccountCheckServlet
 */
public class UpdateAccountCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateAccountCheckServlet() {
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
		//会員情報更新チェック用サーブレット
		request.setCharacterEncoding("UTF-8");

		String nextPage; //ページ遷移用

		//パスワード入力有無用メッセージ
		String passmess = "新しいパスワード：安全のため表示しません。";

		try {


			HttpSession session = request.getSession(false);
			nextPage = null;
			if((boolean)request.getAttribute("timeout")) {
				nextPage = "sessionTimeOut.jsp";
			}else {

				//入力されたデータを受取
				String user_id = request.getParameter("user_id");
				String name = request.getParameter("name");
				String hurigana = request.getParameter("hurigana");
				String birthday = request.getParameter("birthday");
				String sex = request.getParameter("sex");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				String adPost = request.getParameter("adPost");
				String address = request.getParameter("address");
				String password = request.getParameter("password");
				String password2 = request.getParameter("password2");

				//パスワードの有無によってチェックを変える
				if(password.equals("") && password2.equals("")) {
					//パスワード未入力の時はパスワードをSessionからとってくる。
					//ログイン情報をSessionから取り出す

					UserSessionBean userSB = (UserSessionBean)session.getAttribute("userSB");
					LoginBean loginInfo = userSB.getLoginInfo();

					//情報がない時はログインページへ
					if(loginInfo == null) {
						nextPage = "LoginSelectServlet";
						//フォワード
						RequestDispatcher rd = request.getRequestDispatcher(nextPage);
						rd.forward(request, response);
					}else {
						//パスワード上書き
						password = loginInfo.getPassword();
						password2 = loginInfo.getPassword();
						passmess = "パスワード：変更なし";
					}
				}

				//正規表現チェック
				CtmLoginAcountBeanDAO check = new CtmLoginAcountBeanDAO();
				String registCheck = check.registCheck(name,hurigana,birthday,sex,tel,email,adPost,address,password,password2);

				//チェックの結果によって遷移先を設定
				if(registCheck.equals("")) {
					nextPage = "mypage_check.jsp";
				}else {
					//チェックエラーメッセージをセット
					//入力画面へ戻す
					request.setAttribute("mess", registCheck);
					nextPage = "mypage_change.jsp";
				}

				//値をセット
				request.setAttribute("user_id", user_id);
				request.setAttribute("name", name);
				request.setAttribute("hurigana", hurigana);
				request.setAttribute("birthday", birthday);
				request.setAttribute("sex", sex);
				request.setAttribute("tel", tel);
				request.setAttribute("email", email);
				request.setAttribute("adPost", adPost);
				request.setAttribute("address", address);
				request.setAttribute("password", password);
				request.setAttribute("passmess", passmess);

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
