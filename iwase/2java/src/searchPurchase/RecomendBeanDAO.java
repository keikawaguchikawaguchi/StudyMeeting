package searchPurchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RecomendBeanDAO {
	public List<SearchBooksDetalBean> getListAll(){


		//DB取得結果を格納するリスト
		List<SearchBooksDetalBean>list = new ArrayList<SearchBooksDetalBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/bookshop");
			//コネクションの取得
			con = ds.getConnection();


			//SQLの作成
			String sql = "Select ";
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
			sql += "join publish ";
			sql += "on book.publish_id = publish.publish_id";
			ps = con.prepareStatement(sql);
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



				//取得したデータを基に単語オブジェクトを作成
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
