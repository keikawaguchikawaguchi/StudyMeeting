package master;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MstDeleteBeanDAO {
	public boolean deleteProduct(String isbn){
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
			String sql = "delete from book where isbn = ?";
			ps = con.prepareStatement(sql);


			ps.setString(1, isbn);


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
}
