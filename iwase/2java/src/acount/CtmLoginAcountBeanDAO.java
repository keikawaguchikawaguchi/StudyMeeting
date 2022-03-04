package acount;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CtmLoginAcountBeanDAO {


	public LoginBean loginCheck(String mail, String pass){

		LoginBean LoginInfo = null;

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
			String sql = "select * from user where email = ? and password = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mail);
			ps.setString(2, pass);
			//クエリの実行
			rs = ps.executeQuery();
			//データの取得
			while(rs.next()) {
				//1レコードの情報を取得
				String user_id = rs.getString("user_id");
				String name = rs.getString("name");
				String hurigana = rs.getString("hurigana");
				String birthday = rs.getString("birthday");
				String sex = rs.getString("sex");
				String tel= rs.getString("tel");
				String email = rs.getString("email");
				String adPost = rs.getString("adPost");
				String adress = rs.getString("address");
				String password = rs.getString("password");


				//取得したデータを基に単語オブジェクトを作成
				LoginInfo =	new LoginBean(user_id,name,hurigana,birthday,sex,tel,email,adPost,adress,password);


			}

			String getUser_id = LoginInfo.getUser_id();

			//SQLの作成
			sql = "select * from send where send.user_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, getUser_id);
			//クエリの実行
			rs = ps.executeQuery();
			//データの取得
			while(rs.next()) {
				//1レコードの情報を取得
				String send_post = rs.getString("send_post");
				String send_adress = rs.getString("send_address");





				//取得したデータを基に単語オブジェクトを作成
				LoginInfo.setSend_adress(send_adress);

				LoginInfo.setSend_post(send_post);
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
		return LoginInfo;

	}

	public TaxBean getTax() {


		TaxBean taxBean = null;


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
			String sql = "select * from tax";
			ps = con.prepareStatement(sql);

			//クエリの実行
			rs = ps.executeQuery();
			//データの取得
			while(rs.next()) {
				//1レコードの情報を取得
				int taxID = rs.getInt("tax_id");
				double	tax = rs.getDouble("tax");
				taxBean = new TaxBean(taxID, tax);
			}


		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return taxBean;
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
		return taxBean;

	}

	public boolean masterLogin(String masterID, String masterPass) {
		boolean masterLoginFlag = true;

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
			String sql = "SELECT * FROM master WHERE master_id = ? and master_password = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, masterID);
			ps.setString(2, masterPass);
			//クエリの実行
			rs = ps.executeQuery();
			//データの取得
			rs.last();
			if(rs.getRow() > 0) {
				masterLoginFlag = true;
				while(rs.next()) {
					//1レコードの情報を取得
					String reMasterID = rs.getString("master_id");
					String reMasterPass = rs.getString("master_password");
					System.out.println(reMasterID+"masID");
					System.out.println(reMasterPass+"masPass");
				}
			}else {
				masterLoginFlag = false;
			}

		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return false;
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
		return masterLoginFlag;

	}


	public boolean addRegist(String name, String hurigana, Date birthday, String sex, String tel, String email, String adPost, String address, String password){
		//新規会員登録用

		//新規登録の可否を調べる変数
		int ret = -1;
		//データベースからuse_idを取得する変数
		int userID = 0;

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

			//user_idを取得（user_idを抜けさせないため）
			String sql = "select max(user_id) from user";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				userID = rs.getInt("max(user_id)");
			}

			//会員登録用SQLの作成
			sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userID+1);
			ps.setString(2, name);
			ps.setString(3, hurigana);
			ps.setDate(4, birthday);
			ps.setString(5, sex);
			ps.setString(6, tel);
			ps.setString(7, email);
			ps.setString(8, adPost);
			ps.setString(9, address);
			ps.setString(10, password);

			//クエリの実行
			ret = ps.executeUpdate();

			//データベースに登録できたらtrueを返す。
			if(ret == 1) {
				return true;
			}

		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return false;
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
		return false;
	}


	public String registCheck(String name, String hurigana, String birthday, String sex, String tel, String email, String adPost, String address,String password, String password2) {
		//会員情報入力チェック用関数

		//エラーメッセージ格納用変数
		String ng = "";

		//名前チェック
		if(name.equals("")) {
		ng += "名前を入力してください。<br>";
		}

		//フリガナチェック
		if(hurigana.equals("")) {
		ng += "フリガナを入力してください。<br>";
		}

		//生年月日チェック
		if(birthday.equals("")) {
			ng += "生年月日を入力してください。<br>";
		}

		//電話番号チェック
		if(tel.equals("")) {
			ng += "電話番号を入力してください。<br>";
		}else {
			try {
				//intだと桁数が足りないのでlongで数値変換
				Long.parseLong(tel);
			}catch(Exception e){
				ng += "電話番号は数値で入力してください。<br>";
			}
		}

		//メールアドレスチェック
		if(email.equals("")) {
			ng += "メールアドレスを入力してください。<br>";
		}

		//郵便番号チェック
		if(adPost.equals("")) {
			ng += "郵便番号を入力してください。<br>";
		}else {
			try {
				Integer.parseInt(adPost);
				if(adPost.matches("[0-9]{7}") == false) {
					ng += "郵便番号は7桁で入力してください。<br>";
					}
			}catch(Exception e){
				ng += "郵便番号は数値で入力してください。<br>";
			}
		}

		//住所チェック
		if(address.equals("")) {
			ng += "住所を入力してください。<br>";
		}

		//パスワードチェック
		if(password.equals("")) {
			ng += "パスワードを入力してください。<br>";
		}

		//パスワード2チェック
		if(password2.equals("")) {
			ng += "パスワード（確認）を入力してください。<br>";
		}

		//パスワード・パスワード2が一致するかチェック
		if(password.equals(password2) == false) {
			ng += "パスワードが一致していません。<br>";
		}

		return ng;
	}

	public boolean updateAccount(int user_id, String name, String hurigana, Date birthday, String sex, String tel, String email, String adPost, String address, String password) {
		//データベース更新の可否を調べる変数
		int ret = -1;

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

			//会員更新用SQLの作成
			String sql = "update user set name=?, hurigana=?, birthday=?, sex=?, tel=?, email=?, adPost=?, address=?, password=? where user_id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, hurigana);
			ps.setDate(3, birthday);
			ps.setString(4, sex);
			ps.setString(5, tel);
			ps.setString(6, email);
			ps.setString(7, adPost);
			ps.setString(8, address);
			ps.setString(9, password);
			ps.setInt(10, user_id);

			//クエリの実行
			ret = ps.executeUpdate();

			//データベースに登録できたらtrueを返す。
			if(ret == 1) {
				return true;
			}

		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return false;
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
		return false;
	}



}


