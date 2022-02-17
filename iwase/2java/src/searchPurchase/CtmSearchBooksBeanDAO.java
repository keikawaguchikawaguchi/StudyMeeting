package searchPurchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CtmSearchBooksBeanDAO {

	public List<SearchBooksDetalBean> searchBooks(String bookName, String author, String category){
		int sqlCase = 0;
		String sql = null;



		if(bookName.equals("") == false && author.equals("") == true && category.equals("") == true) {

			sqlCase = 1;

		}else if(bookName.equals("") == true && author.equals("") == false && category.equals("") == true) {

			sqlCase = 2;

		}else if(bookName.equals("") == true && author.equals("") == true && category.equals("") == false) {

			sqlCase = 3;

		}else if(bookName.equals("") == false && author.equals("") == false && category.equals("") == true) {

			sqlCase = 4;

		}else if(bookName.equals("") == false && author.equals("") == true && category.equals("") == false) {

			sqlCase = 5;

		}else if(bookName.equals("") == true && author.equals("") == false && category.equals("") == false) {

			sqlCase = 6;

		}else if(bookName.equals("") == false && author.equals("") == false && category.equals("") == false) {

			sqlCase = 7;

		}

		//DB取得結果を格納するリスト
		//		List<SearchBooksDetalBean>list = new ArrayList<SearchBooksDetalBean>();
		List<SearchBooksDetalBean>list = new ArrayList<SearchBooksDetalBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			//			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/books");
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/bookshop");
			//コネクションの取得
			con = ds.getConnection();

			switch (sqlCase) {
			case 1:

				sql = "Select ";
				sql += "book.isbn,";
				sql += "book.title,";
				sql += "author.name,";
				sql += "book.price, ";
				sql += "book.info, ";
				sql += "book.stock, ";
				sql += "book.image, ";
				sql += "publish.publish_name, ";
				sql += "category.category_name, ";
				sql += "book.sales_date ";
				sql += "From book ";
				sql += "join author ";
				sql += "on book.author_id = author.author_id ";
				sql += "join category ";
				sql += "on book.category_id = category.category_id ";
				sql +="join publish ";
				sql +="on book.publish_id = publish.publish_id ";
				sql +="where title like ?";

				ps = con.prepareStatement(sql);

				ps.setString(1, "%" + bookName + "%");

				break;

			case 2:

				sql = "Select ";
				sql += "book.isbn,";
				sql += "book.title,";
				sql += "author.name,";
				sql += "book.price, ";
				sql += "book.info, ";
				sql += "book.stock, ";
				sql += "book.image, ";
				sql += "publish.publish_name, ";
				sql += "category.category_name, ";
				sql += "book.sales_date ";
				sql += "From book ";
				sql += "join author ";
				sql += "on book.author_id = author.author_id ";
				sql += "join category ";
				sql += "on book.category_id = category.category_id ";
				sql +="join publish ";
				sql +="on book.publish_id = publish.publish_id ";
				sql +="where name like ?";

				ps = con.prepareStatement(sql);

				ps.setString(1, "%" + author + "%");

				break;

			case 3:

				sql = "Select ";
				sql += "book.isbn,";
				sql += "book.title,";
				sql += "author.name,";
				sql += "book.price, ";
				sql += "book.info, ";
				sql += "book.stock, ";
				sql += "book.image, ";
				sql += "publish.publish_name, ";
				sql += "category.category_name, ";
				sql += "book.sales_date ";
				sql += "From book ";
				sql += "join author ";
				sql += "on book.author_id = author.author_id ";
				sql += "join category ";
				sql += "on book.category_id = category.category_id ";
				sql +="join publish ";
				sql +="on book.publish_id = publish.publish_id ";
				sql +="where category_name like ?";

				ps = con.prepareStatement(sql);

				ps.setString(1, "%" + category + "%");

				break;

			case 4:

				sql = "Select ";
				sql += "book.isbn,";
				sql += "book.title,";
				sql += "author.name,";
				sql += "book.price, ";
				sql += "book.info, ";
				sql += "book.stock, ";
				sql += "book.image, ";
				sql += "publish.publish_name, ";
				sql += "category.category_name, ";
				sql += "book.sales_date ";
				sql += "From book ";
				sql += "join author ";
				sql += "on book.author_id = author.author_id ";
				sql += "join category ";
				sql += "on book.category_id = category.category_id ";
				sql +="join publish ";
				sql +="on book.publish_id = publish.publish_id ";
				sql +="where title like ? ";
				sql +="and name like ?";

				ps = con.prepareStatement(sql);

				ps.setString(1, "%" + bookName + "%");
				ps.setString(2, "%" + author + "%");

				break;

			case 5:

				sql = "Select ";
				sql += "book.isbn,";
				sql += "book.title,";
				sql += "author.name,";
				sql += "book.price, ";
				sql += "book.info, ";
				sql += "book.stock, ";
				sql += "book.image, ";
				sql += "publish.publish_name, ";
				sql += "category.category_name, ";
				sql += "book.sales_date ";
				sql += "From book ";
				sql += "join author ";
				sql += "on book.author_id = author.author_id ";
				sql += "join category ";
				sql += "on book.category_id = category.category_id ";
				sql +="join publish ";
				sql +="on book.publish_id = publish.publish_id ";
				sql +="where title like ? ";
				sql +="and category_name like ?";

				ps = con.prepareStatement(sql);

				ps.setString(1, "%" + bookName + "%");
				ps.setString(2, "%" + category + "%");

				break;

			case 6:

				sql = "Select ";
				sql += "book.isbn,";
				sql += "book.title,";
				sql += "author.name,";
				sql += "book.price, ";
				sql += "book.info, ";
				sql += "book.stock, ";
				sql += "book.image, ";
				sql += "publish.publish_name, ";
				sql += "category.category_name, ";
				sql += "book.sales_date ";
				sql += "From book ";
				sql += "join author ";
				sql += "on book.author_id = author.author_id ";
				sql += "join category ";
				sql += "on book.category_id = category.category_id ";
				sql +="join publish ";
				sql +="on book.publish_id = publish.publish_id ";
				sql +="where name like ? ";
				sql +="and category_name like ?";

				ps = con.prepareStatement(sql);

				ps.setString(1, "%" + author + "%");
				ps.setString(2, "%" + category + "%");

				break;

			case 7:

				sql = "Select ";
				sql += "book.isbn,";
				sql += "book.title,";
				sql += "author.name,";
				sql += "book.price, ";
				sql += "book.info, ";
				sql += "book.stock, ";
				sql += "book.image, ";
				sql += "publish.publish_name, ";
				sql += "category.category_name, ";
				sql += "book.sales_date ";
				sql += "From book ";
				sql += "join author ";
				sql += "on book.author_id = author.author_id ";
				sql += "join category ";
				sql += "on book.category_id = category.category_id ";
				sql +="join publish ";
				sql +="on book.publish_id = publish.publish_id ";
				sql +="where name like ? ";
				sql +="and category_name like ?";
				sql +="and title like ?";

				ps = con.prepareStatement(sql);

				ps.setString(1, "%" + author + "%");
				ps.setString(2, "%" + category + "%");
				ps.setString(3, "%" + bookName + "%");

				break;
			}


			//クエリの実行
			rs = ps.executeQuery();
			//データの取得
			while(rs.next()) {
				//1レコードの情報を取得
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				String author_name = rs.getString("name");
				String price = rs.getString("price");
				String info = rs.getString("info");
				String stock= rs.getString("stock");
				String image = rs.getString("image");
				String publish_name = rs.getString("publish_name");
				String category_name = rs.getString("category_name");
				String sales_date = rs.getString("sales_date");


				SearchBooksDetalBean searchResurtbooks =
						new SearchBooksDetalBean(isbn,title,author_name,price,info,stock,
								image,publish_name,category_name,sales_date);

				list.add(searchResurtbooks);

			}

		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return null;
		}finally{
			try{
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
				//データベースに接続されていれば切断する
				if(con != null){
					con.close();
				}
			}catch(Exception ex){
				System.err.println(ex.getMessage());
			}
		}
		return list;

	}




}
