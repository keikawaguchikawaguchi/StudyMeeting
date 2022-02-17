package master;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MstChangeBeanDAO {

	public boolean changtProduct(String searchIsbn,String isbn, String title, String authorID,
			int price, String categoryID, String info,int stock, String image,
			String publishID, Date sales_date){
		boolean addProductFlag = false;

		//DB取得結果を格納するリスト
		Connection con = null;
		PreparedStatement ps = null;
		int ret = 0;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/bookshop");
			//コネクションの取得
			con = ds.getConnection();


			//SQLの作成
			String sql = "update book set ";
			sql += "isbn = ?,";
			sql += "title = ?,";
			sql += "author_id = ?,";
			sql += "price = ?,";
			sql += "category_id = ?,";
			sql += "info = ?,";
			sql += "stock = ?,";
			sql += "image = ?, ";
			sql += "publish_id = ?,";
			sql += "sales_date = ? ";
			sql += "where isbn = ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, isbn);
			ps.setString(2, title);
			ps.setString(3, authorID);
			ps.setInt(4, price);
			ps.setString(5, categoryID);
			ps.setString(6, info);
			ps.setInt(7, stock);
			ps.setString(8, image);
			ps.setString(9, publishID);
			ps.setDate(10,  sales_date);
			ps.setString(11, searchIsbn);

			//クエリの実行
			ret = ps.executeUpdate();
			//データの取得
			if(ret > 0) {
				addProductFlag = true;
			}

		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return addProductFlag;
		}finally{
			try{

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
		return addProductFlag;

	}

	public boolean changtTax(double tax){
		boolean flag = false;

		//DB取得結果を格納するリスト
		Connection con = null;
		PreparedStatement ps = null;
		int ret = 0;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/bookshop");
			//コネクションの取得
			con = ds.getConnection();


			//SQLの作成
			String sql = "update tax set tax = ?  ";

			ps = con.prepareStatement(sql);
			ps.setDouble(1, tax);

			//クエリの実行
			ret = ps.executeUpdate();
			//データの取得
			if(ret > 0) {
				flag = true;
			}

		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return flag;
		}finally{
			try{

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
		return flag;

	}


}