package acount;

import java.util.ArrayList;
import java.util.List;

public class LoginBean {
	//フィールド---------------------------
	private String user_id;
	private String name;
	private String hurigana;
	private String birthday;
	private String sex;
	private String tel;
	private String email;
	private String adPost;
	private String address;
	private String password;
	private List<String> send_adress;
	private List<String> send_post;



		//コンストラクタ-------------------------
		public LoginBean() {}
		public LoginBean(String user_id,String name,String hurigana,String birthday,String sex,String tel,
				String email,String adPost,String address,String password) {

			this.user_id = user_id;
			this.name = name;
			this.hurigana = hurigana;
			this.birthday = birthday;
			this.sex = sex;
			this.tel = tel;
			this.email = email;
			this.adPost = adPost;
			this.address = address;
			this.password = password;

			this.send_adress = new ArrayList<String>();
			this.send_post = new ArrayList<>();

			}


		//メソッド-------------------------
		public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getHurigana() {
			return hurigana;
		}
		public void setHurigana(String hurigana) {
			this.hurigana = hurigana;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getAdPost() {
			return adPost;
		}
		public void setAdPost(String adPost) {
			this.adPost = adPost;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public List<String> getSend_adress() {
			return send_adress;
		}
		public void setSend_adress(String send_adress) {
			this.send_adress.add(send_adress);
		}
		public List<String> getSend_post() {
			return send_post;
		}
		public void setSend_post(String send_post) {
			this.send_post.add(send_post);
		}


}
