package master;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MsterServlet
 */
public class MasterSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MasterSelectServlet() {
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
		String mstSlectNum = request.getParameter("mstSelectNum");
		String nextPage = null;
		MstListBeanDAO mstLBDAO = new MstListBeanDAO();
		SubProductBeanDAO subPBDAO = new SubProductBeanDAO();
		ProductSubBean pSB =  subPBDAO.getProductSub();
		request.setAttribute("pSB", pSB);
		switch (mstSlectNum) {
		case "1":
			List<ProductBean> productList = mstLBDAO.getProductList();
			request.setAttribute("productList", productList);



			nextPage = "product.jsp";
			break;

		case "5":
			double tax = mstLBDAO.getTax();
			request.setAttribute("tax", tax);



			nextPage = "tax.jsp";
			break;




		}

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);

	}

}
