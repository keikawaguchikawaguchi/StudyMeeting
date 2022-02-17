package master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SubProductBeanDAO {

	public ProductSubBean getProductSub(){
		Map<String,String> authorMap = new HashMap<String, String>();
		Map<String,String> categoryMap = new HashMap<String, String>();
		Map<String,String> publishMap = new HashMap<String, String>();
		ProductSubBean productSubBean = null;


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
			String sql = "select * from author";
			ps = con.prepareStatement(sql);

			//クエリの実行
			rs = ps.executeQuery();
			//データの取得
			while(rs.next()) {
				authorMap.put(rs.getNString("author_id"), rs.getString("name"));
			}

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

			con = ds.getConnection();


			//SQLの作成
			sql = "select * from category";
			ps = con.prepareStatement(sql);

			//クエリの実行
			rs = ps.executeQuery();
			//データの取得
			while(rs.next()) {
				categoryMap.put(rs.getNString("category_id"), rs.getString("category_name"));
			}

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

			con = ds.getConnection();


			//SQLの作成
			sql = "select * from publish";
			ps = con.prepareStatement(sql);

			//クエリの実行
			rs = ps.executeQuery();
			//データの取得
			while(rs.next()) {
				publishMap.put(rs.getNString("publish_id"), rs.getString("publish_name"));
			}

			 productSubBean = new ProductSubBean(authorMap, categoryMap, publishMap);


		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return productSubBean;
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
		return productSubBean;

	}


}
