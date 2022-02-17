<?php

//htmlのテンプレートとfunction,DBInfo,cssを読み込み
require 'head.php'

?>
<title>社員情報表示</title>
</head>

<main class="infoMain">


	<?php
	//--------------------------------セッション＆エラー管理---------------------------------------
	//DBに接続するためにDBInfoをインスタンス生成
	$dbInfo = new WorkManage\DBInfo();

	//セッションにIDが入っていない(URL直打ちなど)場合、エラーが起きてfalse
	//index.phpへ遷移する
	$checkFlag = check(($_SESSION['id']));
	error($checkFlag, 'screenErrMes', 'index.php');

	$constEnum = new WorkManage\ConstEnum();

	echo "<br>";
	if ($_SESSION['su'] == $constEnum->getUSER()) {

		//ユーザーIDを入れる場所
		$userList0 = $dbInfo->select($_SESSION['id']);

		error($userList0, 'dbErrMes', 'index.php');

		//--------------------------------一般ユーザー----------------------------------------------
		foreach ($userList0 as $row => $value) {


			echo '<p><table border="1" class="infoTable">';
			echo '<tr><td class="infoTdLeft">名前</td>';
			echo '<td class="infoTdRight">';
			echo $value['name'];
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>フリガナ</td>';
			echo '<td>';
			echo $value['furi'];
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>入社日</td>';
			echo '<td>';
			echo str_replace("-", "/", $value['enterdate']);

			echo '</td>';
			echo '</tr>';

			echo '<tr><td>勤続年月</td>';
			echo '<td>';
			echo interval($value['enterdate']);
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>郵便番号</td>';
			echo '<td>';
			// echo substr($value['postno'], 0, 3) . "-" . substr($value['postno'], 3);
			echo $value['postno'];
			echo '</td>';
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>住所</td>';
			echo '<td>';
			echo $value['address'];
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>電話番号</td>';
			echo '<td>';
			echo Telformat($value['tel']);
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>生年月日</td>';
			echo '<td>';
			echo str_replace("-", "/", $value['birth']);
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>年齢</td>';
			echo '<td>';
			echo age($value['birth']);
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>雇用保険被保険者番号</td>';
			echo '<td>';
			echo $value['eino'];
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>退職日</td>';
			echo '<td>';
			echo $value['retire'];
			echo '</td>';
			echo '</tr>';
			echo '</table></p>';

			echo '<p><table border="1" class="infoTable">';
			echo '<tr><td class="infoTdLeft">デスクネッツパスワード</td>';
			echo '<td class="infoTdRight">';
			echo $value['desknetspass'];
			echo '</td>';
			echo '</tr>';
			echo '</table></p>';

			echo '<p><table border="1" class="infoTable">';
			echo '<tr><td class="infoTdLeft">共有　ユーザー名</td>';
			echo '<td class="infoTdRight">';
			echo $value['shareuser'];
			echo '</td>';
			echo '</tr>';

			echo '<tr><td class="infoTdLeft">共有　パスワード</td>';
			echo '<td class="infoTdRight">';
			echo $value['sharepass'];
			echo '</td>';
			echo '</tr>';
			echo '</table></p>';

			echo '<p><table border="1" class="infoTable">';
			echo '<tr><td class="infoTdLeft">メールアドレス</td>';
			echo '<td class="infoTdRight">';
			echo $value['emailaddress'];

			echo '</td>';
			echo '</tr>';

			echo '<tr><td>メールパスワード</td>';
			echo '<td>';
			echo $value['emailpass'];
			echo '</td>';
			echo '</tr>';

			echo '</table></p>';
		}
		echo '<div class="infoUserHref">';
		echo '<a href = "license.php">資格保有情報</a>';
		echo '<a href = "infoproc/logout.php">ログアウト</a>';
		echo '<a href = "edit.php?">修正</a>';
		echo '</div>';

		//------------------------------管理者ユーザー------------------------------
	} elseif ($_SESSION['su'] == $constEnum->getADMIN()) {
			//ユーザーIDを入れる場所
		$id = $_GET['id'];
		$userList1 = $dbInfo->select($_GET['id']);
		error($userList1, 'dbErrMes', 'index.php');

		foreach ($userList1 as $row => $value) {

			echo '<p><table border="1" class="infoTable">';
			echo '<tr><td class="infoTdLeft">名前</td>';
			echo '<td class="infoTdRight">';
			echo $value['name'];
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>フリガナ</td>';
			echo '<td>';
			echo $value['furi'];
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>入社日</td>';
			echo '<td>';
			echo str_replace("-", "/", $value['enterdate']);
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>勤続年月</td>';
			echo '<td>';
			echo interval($value['enterdate']);
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>郵便番号</td>';
			echo '<td>';
			// echo substr($value['postno'], 0, 3) . "-" . substr($value['postno'], 3);
			echo $value['postno'];
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>住所</td>';
			echo '<td>';
			echo $value['address'];
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>電話番号</td>';
			echo '<td>';
			echo telFormat($value['tel']);;

			echo '</td>';
			echo '</tr>';

			echo '<tr><td>生年月日</td>';
			echo '<td>';
			echo str_replace("-", "/", $value['birth']);
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>年齢</td>';
			echo '<td>';
			echo age($value['birth']);
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>社会保険</td>';
			if ($value['si'] == 0) {
				echo '<td>';
				echo '';
				echo '</td>';
				echo '</tr>';
			} elseif ($value['si'] == 1) {
				echo '<td>';
				echo 'レ';
				echo '</td>';
				echo '</tr>';
			}

			echo '<tr><td>雇用保険</td>';
			if ($value['ei'] == 0) {
				echo '<td>';
				echo '';
				echo '</td>';
				echo '</tr>';
			} elseif ($value['ei'] == 1) {
				echo '<td>';
				echo 'レ';
				echo '</td>';
				echo '</tr>';
			}

			echo '<tr><td>雇用保険被保険者番号</td>';
			echo '<td>';
			echo $value['eino'];
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>退職日</td>';
			echo '<td>';
			echo $value['retire'];
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>社会保険損失</td>';
			if ($value['si_loss'] == 0 || $value['si_loss'] == null) {
				echo '<td>';
				echo '';
				echo '</td>';
				echo '</tr>';
			} elseif ($value['si_loss'] == 1) {
				echo '<td>';
				echo 'レ';
				echo '</td>';
				echo '</tr>';
			}

			echo '<tr><td>雇用保険損失</td>';
			if ($value['ei_loss'] == 0 || $value['ei_loss'] == null) {
				echo '<td>';
				echo '';
				echo '</td>';
				echo '</tr>';
			} elseif ($value['ei_loss'] == 1) {
				echo '<td>';
				echo 'レ';
				echo '</td>';
				echo '</tr>';
			}
			echo '</table></p>';

			echo '<p><table border="1" class="infoTable">';
			echo '<tr><td class="infoTdLeft">デスクネッツパスワード</td>';
			echo '<td class="infoTdRight">';
			echo $value['desknetspass'];
			echo '</td>';
			echo '</tr>';
			echo '</table></p>';

			echo '<p><table border="1" class="infoTable">';
			echo '<tr><td class="infoTdLeft">共有　ユーザー名</td>';
			echo '<td class="infoTdRight">';
			echo $value['shareuser'];
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>共有　パスワード</td>';
			echo '<td>';
			echo $value['sharepass'];
			echo '</td>';
			echo '</tr>';
			echo '</table></p>';

			echo '<p><table border="1" class="infoTable">';
			echo '<tr><td class="infoTdLeft">メールアドレス</td>';
			echo '<td class="infoTdRight">';
			echo $value['emailaddress'];
			echo '</td>';
			echo '</tr>';

			echo '<tr><td>メールパスワード</td>';
			echo '<td>';
			echo $value['emailpass'];
			echo '</td>';
			echo '</tr>';
			echo '</table></p>';

			echo '<p><table border="1" class="infoTable">';
			echo '<tr><td class="infoTdLeft">社員情報管理権限</td>';
			if ($value['su'] == 0) {
				echo '<td class="infoTdRight">';
				echo '一般ユーザー';
				echo '</td>';
				echo '</tr>';
			} elseif ($value['su'] == 1) {
				echo '<td class="infoTdRight">';
				echo '管理ユーザー';
				echo '</td>';
				echo '</tr>';
			}
			echo '</table></p>';
		}

		echo '<div class="infoAdminHref">';
		echo '<a href = "list.php">一覧</a>';
		echo '<a href = "license.php?id=', $id, '">資格保有情報</a><br><br>';
		echo '<a href = "infoproc/logout.php">ログアウト</a>';
		echo '<a href = "edit.php?id=', $id, '">修正</a>';
		echo '<a href = "delete.php?id=', $id, '">削除</a>';
		echo '</div>';
	}
	?>
</main>