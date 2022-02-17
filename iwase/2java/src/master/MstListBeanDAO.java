package master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MstListBeanDAO {

	public  List<ProductBean> getProductList(){
		List<ProductBean> productList = new ArrayList<ProductBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソース指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/bookshop");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "select * from book";
			//PreparedStatementオブジェクトを生成
			ps = con.prepareStatement(sql);
			//SQL文を実行
			rs = ps.executeQuery();

			//結果を取得
			while(rs.next()){
				//1レコードの情報を取得
				//ISBN 1
				String isbn=rs.getString("isbn");
				String title=rs.getString("title");
				String author=rs.getString("author_id");
				int price=rs.getInt("price");
				String category=rs.getString("category_id");
				String info=rs.getString("info");
				int stock=rs.getInt("stock");
				String image=rs.getString("image");
				String publish=rs.getString("publish_id");
				Date sales_date=rs.getDate("sales_date");

				//取得データからオブジェクト作成
				ProductBean all = new ProductBean(isbn,title,author,price,
						category,info,stock,image,publish,sales_date);
				//リストに追加
				productList.add(all);
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
		return productList;
	}

	public  double getTax(){
		double tax = -1.0;
				Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソース指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/bookshop");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "select * from tax";
			//PreparedStatementオブジェクトを生成
			ps = con.prepareStatement(sql);
			//SQL文を実行
			rs = ps.executeQuery();

			//結果を取得
			while(rs.next()){
				//1レコードの情報を取得
				//ISBN 1
				tax = rs.getDouble("tax");

			}
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return -1.0;
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
		return tax;
	}

}
