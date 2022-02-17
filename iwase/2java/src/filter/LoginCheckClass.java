package filter;

public class LoginCheckClass {

	public LoginCheckClass() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static String  loginOk(String userName) {

		String str = "";
		str += "<header>";
		str += "	<div id='logo'>";
		str += "		<a href='IndexServlet'><img src='img/logo.svg' alt=''></a>";
		str += "		<p>ようこそ"+ userName +"さん</p>";
		str += "	</div>";
		str += "	<div id='search'>";
		str += "		<form action='SearchServlet' method='get'>";
		str += "			<div id='tablebutton1'>";
		str += "				<div style='width:100px;'>";
		str += "					<p>タイトル</p>";
		str += "					<p>著者名</p>";
		str += "					<p>カテゴリー名</p>";
		str += "				</div>";
		str += "				<div>";
		str += "					<p><input type='text' name='bookName'></p>";
		str += "					<p><input type='text' name='author'></p>";
		str += "					<p><input type='text' name='category'></p>";
		str += "				</div>";
		str += "				<div style='margin-top: 10px;'>";
		str += "					<p><input type='submit' value='検索' class='btn'></p>";
		str += "					<p><input type='submit' name='allBooks' value='商品一覧' class='btn'></p>";
		str += "				</div>";
		str += "			</div>";
		str += "		</form>";
		str += "		<div id='button'>";
		str += "			<form action='LogoutPageServlet' method='get'>";
		str += "				<input type='submit' value='ログアウト' class='btn2'>";
		str += "			</form >";
		str += "			<form action='MypageServlet' method='get'>";
		str += "				<input type='submit' value='マイページ' class='btn2'>";
		str += "			</form >";
		str += "			<form action='StraightCartServlet' method='get'>";
		str += "				<input type='submit' value='買い物かご' class='btn2'>";
		str += "			</form >";
		str += "		</div>";
		str += "	</div>";
		str += "</header>";

		return str;
	}

	public static String loginNo() {

		String str = "";
		str += "<header>";
		str += "	<div id='logo'>";
		str += "		<a href='IndexServlet'><img src='img/logo.svg' alt=''></a>";
		str += "	</div>";
		str += "	<div id='search'>";
		str += "		<form action='SearchServlet' method='get'>";
		str += "			<div id='tablebutton1'>";
		str += "				<div>";
		str += "					<p>タイトル</p>";
		str += "					<p>著者名</p>";
		str += "					<p>カテゴリー名</p>";
		str += "				</div>";
		str += "				<div>";
		str += "					<p><input type='text' name='bookName'></p>";
		str += "					<p><input type='text' name='author'></p>";
		str += "					<p><input type='text' name='category'></p>";
		str += "				</div>";
		str += "				<div style='margin-top: 10px;'>";
		str += "					<p><input type='submit' value='検索' class='btn'></p>";
		str += "					<p><input type='submit' name='allBooks' value='商品一覧' class='btn'></p>";
		str += "				</div>";
		str += "			</div>";
		str += "		</form>";
		str += "		<div id='button'>";
		str += "			<form action='LoginPageServlet' method='get'>";
		str += "				<input type='submit' value='ログイン画面' class='btn2'>";
		str += "			</form >";
		str += "		</div>";
		str += "	</div>";
		str += "</header>";

		return str;
	}

}
