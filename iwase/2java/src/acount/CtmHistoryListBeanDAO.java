package acount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CtmHistoryListBeanDAO {

	public  List<HistoryBean> getHistoryList(int userID){
		List<HistoryBean> historyList = new ArrayList<HistoryBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int maxPurchaseID = 0;

		try {
			//コンテキストの取得
			Context context = new InitialContext();

			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/bookshop");

			//コネクション取得
			con = ds.getConnection();


			//SQLの作成(購入情報)
			String sql = "SELECT * FROM purchase where user_id = ? and cansel_flag = 0 ";
			sql += "ORDER BY purchase_id DESC";


			//PreparedStatementオブジェクトを生成
			ps = con.prepareStatement(sql);

			//パラメータの指定  セッションのuser_idの値を入れる
			ps.setInt(1, userID);

			//SQL文を実行
			rs = ps.executeQuery();

			//結果を取得
			while(rs.next()){
				int purchaseID = rs.getInt("purchase_id");					//注文番号
				Timestamp purchaseDate = rs.getTimestamp("purchase_data");	//注文日
				String selectAdpost = rs.getString("select_adpost");			//送付先郵便番号
				String selectAddress = rs.getString("select_address");		//送付先住所
				double tax = rs.getDouble("tax");								//タックス

				HistoryBean historyBean = new HistoryBean(purchaseID,
						purchaseDate, selectAdpost, selectAddress, tax);

				historyList.add(historyBean);

			}

		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return historyList;

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
		return historyList;
	}



	public  List<HistoryBooksBean> getHistoryBooksList(int searchUserID){
		List<HistoryBooksBean> historyBBList = new ArrayList<HistoryBooksBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;


		try {
			//コンテキストの取得
			Context context = new InitialContext();

			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/bookshop");

			//コネクション取得
			con = ds.getConnection();


			//SQLの作成(購入情報)
			String sql = "SELECT * FROM purchase_dateil " +
					"INNER JOIN purchase ON purchase_dateil.purchase_id = purchase.purchase_id "+
					"INNER JOIN book ON purchase_dateil.isbn =  book.isbn " +
					"INNER JOIN category ON book.category_id = category.category_id " +
					"INNER JOIN author ON book.author_id = author.author_id " +
					"INNER JOIN publish ON book.publish_id = publish.publish_id "+
					"where purchase.user_id = ?";


			//PreparedStatementオブジェクトを生成
			ps = con.prepareStatement(sql);

			//パラメータの指定  セッションのuser_idの値を入れる
			ps.setInt(1, searchUserID);

			//SQL文を実行
			rs = ps.executeQuery();

			//結果を取得
			while(rs.next()){
				int purchaseID = rs.getInt("purchase_id");					//注文番号
				String title = rs.getString("title");							//商品タイトル名
				int purchaseCount = rs.getInt("purchase_count");				//注文商品ごとの注文数
				String isbn = rs.getString("isbn");								//isbn
				int price = rs.getInt("price");								//価格
				String author = rs.getString("name");
				String category = rs.getString("category_name");
				String publish = rs.getString("publish_name");
				String info = rs.getString("info");
				String image = rs.getString("image");

				HistoryBooksBean historyBB = new HistoryBooksBean(purchaseID, isbn, title,
						author, price, info, image, publish, category, purchaseCount);

				historyBBList.add(historyBB);

			}

		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return historyBBList;

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
		return historyBBList;
	}

}
