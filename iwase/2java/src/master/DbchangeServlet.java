package master;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DbchangeServlet
 */
public class DbchangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DbchangeServlet() {
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
		String btn = request.getParameter("btn");
		System.out.println(btn);
		String searchIsbn = request.getParameter("searchisbn");
		System.out.println(searchIsbn);
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String authorID = request.getParameter("author");
		String publishID = request.getParameter("publish");
		String categoryID = request.getParameter("category");
		int price = Integer.parseInt(request.getParameter("price"));
		int stock =Integer.parseInt(request.getParameter("stock"));
		Date sales_date = Date.valueOf(request.getParameter("sales_date"));
		String explanation = request.getParameter("explanation");
		String image = request.getParameter("image");
		boolean mstDbFlag = false;
		if(btn.equals("変更")) {

			MstChangeBeanDAO mstChange = new MstChangeBeanDAO();
			mstDbFlag = mstChange.changtProduct(searchIsbn,isbn, title, authorID, price,
					categoryID, explanation, stock,image, publishID, sales_date);
			request.setAttribute("mstDbFlag", mstDbFlag);

		}else if (btn.equals("追加")) {
			MstInsertBeanDAO mstInsert = new MstInsertBeanDAO();
			mstDbFlag = mstInsert.insertProduct(isbn, title, authorID, price, categoryID, explanation,
					stock, image, publishID, sales_date);
			request.setAttribute("mstDbFlag", mstDbFlag);

		}else if(btn.equals("削除")){
			MstDeleteBeanDAO mstDelete = new MstDeleteBeanDAO();
			mstDbFlag = mstDelete.deleteProduct(searchIsbn);
			request.setAttribute("mstDbFlag", mstDbFlag);
		}

		RequestDispatcher rd = request.getRequestDispatcher("mstDbresult.jsp");
		rd.forward(request, response);


	}



}
