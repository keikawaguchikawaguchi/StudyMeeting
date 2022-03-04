package acount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CancelBeanDAO {

	public void getListByCan(int pID){

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
			String sql = "UPDATE purchase set cansel_flag = ? Where purchase_id = ?";

			//PreparedStatementオブジェクトを生成
			ps = con.prepareStatement(sql);

			//パラメータの指定  セッションのuser_idの値を入れる
			ps.setInt(1, 1);
			ps.setInt(2, pID);

			//SQL文を実行
			ps.executeUpdate();

		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return;

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
		return;
	}
}
