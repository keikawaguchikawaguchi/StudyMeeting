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
 * Servlet implementation class UpdateAccount
 */
public class UpdateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAccountServlet() {
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
		//会員の登録情報を表示させるサーブレット

		//ページ遷移用変数
		String nextPage;

		try {
			//ログイン確認
			HttpSession session = request.getSession(false);
			nextPage = null;
			if((boolean)request.getAttribute("timeout")) {
				nextPage = "sessionTimeOut.jsp";
			}else {

				//ログイン情報をSessionから取り出す

				UserSessionBean userSB = (UserSessionBean)session.getAttribute("userSB");
				LoginBean loginInfo = userSB.getLoginInfo();

				//情報がない時はログインページへ
				if(loginInfo == null) {
					nextPage = "LoginSelectServlet";
				}else {

					//情報取り出し
					String userId = loginInfo.getUser_id();
					String name = loginInfo.getName();
					String hurigana = loginInfo.getHurigana();
					String birthday = loginInfo.getBirthday();
					String sex = loginInfo.getSex();
					String tel = loginInfo.getTel();
					String email = loginInfo.getEmail();
					String adPost = loginInfo.getAdPost();
					String address = loginInfo.getAddress();
					String password = loginInfo.getPassword();

					//データをセット
					request.setAttribute("user_id", userId);
					request.setAttribute("name", name);
					request.setAttribute("hurigana", hurigana);
					request.setAttribute("birthday", birthday);
					request.setAttribute("sex", sex);
					request.setAttribute("tel", tel);
					request.setAttribute("email", email);
					request.setAttribute("adPost", adPost);
					request.setAttribute("address", address);
					request.setAttribute("password", password);

					nextPage ="mypage_change.jsp";

				}
			}
				//フォワード
				RequestDispatcher rd = request.getRequestDispatcher(nextPage);
				rd.forward(request, response);


			}catch (Exception ex) {
				//エラー発生時
				String mess = "エラーが発生しました。<br>";
				request.setAttribute("mess", mess);
				nextPage = "error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(nextPage);
				rd.forward(request, response);
			}
	}

}
