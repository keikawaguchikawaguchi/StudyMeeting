package searchPurchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CartDBCheckBeanDAO {


	public String[] getIsbnPurOfNum(String searchIsbn){
		String[] isbnNumOfPur = new String[2];


		//DB取得結果を格納するリスト
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
			String sql = "select * from book where isbn = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, searchIsbn);



			//クエリの実行
			rs = ps.executeQuery();
			//データの取得
			rs.next();
			String isbn = rs.getString("isbn");
			int stock = rs.getInt("stock");

			isbnNumOfPur[0]  = isbn;
			isbnNumOfPur[1] = String.valueOf(stock);
			System.out.println(isbn +"isbn");
			System.out.println(stock+"stock");


		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return isbnNumOfPur;
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
		return isbnNumOfPur;

	}
}
