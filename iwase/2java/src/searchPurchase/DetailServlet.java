package searchPurchase;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DetalServlet
 */
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nextPage = null;

		if((boolean)request.getAttribute("timeout")) {
			nextPage = "sessionTimeOut.jsp";
		}else {
			nextPage="book.jsp";

			String isbn = request.getParameter("isbn");
			System.out.println(isbn+"ディテイルのISBN");
			CtmBooksBeanDAO booksBeanDAO= new CtmBooksBeanDAO();
			SearchBooksDetalBean searchBDB = booksBeanDAO.getSearchBDB(isbn);
			System.out.println(searchBDB+"デティールのsearchBDB");
			String loginHed = (String)request.getAttribute("loginHed");
			request.setAttribute("loginHed", loginHed);


			request.setAttribute("searchBDB",searchBDB);
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
