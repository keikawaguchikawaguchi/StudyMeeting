package searchPurchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CtmCartBeanDAO {

	public boolean insertPurchase(int userID, String selectAdPost, String selectAddress, double taxID,
			Map<String, CartBean> mapCartBean) {

		boolean insertFlag = true;

		//DB取得結果を格納するリスト

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int ret = -1;
		int purchaseID =1;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/bookshop");
			//コネクションの取得
			con = ds.getConnection();
			System.out.println("購入履歴追加一回目");
			String sql = "select max(purchase_id) from purchase";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				purchaseID = rs.getInt("max(purchase_id)");
			}


			long millis = System.currentTimeMillis();
			System.out.println("購入履歴追加２回目");
			//SQLの作成
			sql = "INSERT into purchase VALUES(?,?,?,?,?,n?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, purchaseID+1);
			ps.setInt(2, userID);
			ps.setTimestamp(3, new Timestamp(millis));
			ps.setInt(4, 0);
			ps.setString(5, selectAdPost);
			ps.setString(6, selectAddress);
			ps.setDouble(7, taxID);
			//クエリの実行
			ret = ps.executeUpdate();
			System.out.println("購入履歴追加３回目");
			for(CartBean cartBean : mapCartBean.values()) {
				SearchBooksDetalBean searchBDB = cartBean.getSearchBDB();
				sql = "INSERT into purchase_dateil VALUES(?,?,?,null)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, purchaseID+1);
				ps.setString(2, searchBDB.getIsbn());
				ps.setInt(3, cartBean.getNumOfPur());
				ret = ps.executeUpdate();

				sql = " UPDATE book set stock = ? WHERE isbn = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(searchBDB.getStock()) - cartBean.getNumOfPur());
				ps.setString(2, searchBDB.getIsbn());
				ret = ps.executeUpdate();
			}



		}catch(Exception ex){
			System.err.println(ex.getMessage());
			insertFlag = false;

		}finally{
			System.out.println(ret + "更新件数");
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
		return insertFlag;

	}



}
