package searchPurchase;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//検索ボックス（書籍名、著者名、カテゴリー）に入っている値によって処理を分ける
		//何もなければ一覧表示
		//パラメーター名は仮
		String nextPage = null;
		if((boolean)request.getAttribute("timeout")){
			nextPage = "sessionTimeOut.jsp";
		}else {
			nextPage = "result.jsp";
		}

		String allBooks = request.getParameter("allBooks");
		String bookName =  request.getParameter("bookName");
		String author =  request.getParameter("author");
		String category =  request.getParameter("category");


		String loginHed = (String)request.getAttribute("loginHed");
		request.setAttribute("loginHed",loginHed);

		CtmBooksBeanDAO booksBeanDAO= new CtmBooksBeanDAO();

		if(allBooks != null){
			//
			List<SearchBooksDetalBean> searchBDBList = booksBeanDAO.getListAll();

			request.setAttribute("searchBDBList", searchBDBList);
			request.setAttribute("searchResultFlag", true);

		}else {

			if(bookName.trim() == "" && author.trim() == "" && category.trim() == "") {
				request.setAttribute("searchResultFlag", false);
				request.setAttribute("kara", "空");

			}else {

				CtmSearchBooksBeanDAO searchDao = new CtmSearchBooksBeanDAO();

				List<SearchBooksDetalBean> searchBDBList = searchDao.searchBooks(bookName,author,category);
				request.setAttribute("searchBDBList", searchBDBList);
				if(searchBDBList.size() == 0) {
					request.setAttribute("searchResultFlag", false);
				}else {
					request.setAttribute("searchResultFlag", true);
				}

			}
		}

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);

	}





	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
