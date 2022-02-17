package acount;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistCheckServlet
 */
public class RegistCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistCheckServlet() {
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
		//新規会員登録入力チェック用サーブレット
		request.setCharacterEncoding("UTF-8");

		String nextPage;

		try {
			//入力されたデータを受取
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

			//正規表現チェック
			CtmLoginAcountBeanDAO check = new CtmLoginAcountBeanDAO();
			String registCheck = check.registCheck(name,hurigana,birthday,sex,tel,email,adPost,address,password,password2);


			if((boolean)request.getAttribute("timeout")){
				nextPage = "sessionTimeOut.jsp";
			}else {


				//チェックの結果によって遷移先を設定
				if(registCheck.equals("")) {
					nextPage = "regist_check.jsp";
				}else {
					request.setAttribute("mess", registCheck);
					nextPage = "regist.jsp";
				}

				//値をセット
				request.setAttribute("name", name);
				request.setAttribute("hurigana", hurigana);
				request.setAttribute("birthday", birthday);
				request.setAttribute("sex", sex);
				request.setAttribute("tel", tel);
				request.setAttribute("email", email);
				request.setAttribute("adPost", adPost);
				request.setAttribute("address", address);
				request.setAttribute("password", password);

				//フォワード
				RequestDispatcher rd = request.getRequestDispatcher(nextPage);
				rd.forward(request, response);
			}
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
