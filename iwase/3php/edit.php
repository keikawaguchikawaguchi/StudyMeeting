<?php

//htmlのテンプレートとfunction,DBInfo,cssを読み込み
require 'head.php'

?>
<title>社員情報修正</title>
</head>
<body>
	<main class="infoMain">

		<a id="newErrMes">
			<?php
			$errMes = getError();
			if (isset($errMes)) {
				echo $errMes;
				unset($_SESSION['err']);
			}
			?>
		</a>
		<?php

		$constEnum = new WorkManage\ConstEnum();

		//---社員情報管理者権限---
		//　一般ユーザー[0]
		$USER = $constEnum->getUSER();
		//管理者ユーザー[1]
		$ADMIN = $constEnum->getADMIN();
		//　退職ユーザー[2]
		$NOT_USER = $constEnum->getNON_USER();

		//保険未加入[0]
		$NOT_SUBSCRIBED = $constEnum->getNOT_SUBSCRIBED();
		//保険加入済み[1]
		$SUBSCRIBED = $constEnum->getSUBSCRIBED();

		//保険損失済み[0]
		$NOT_LOSSED = $constEnum->getNOT_LOSSED();
		//保険損失済み[1]
		$LOSSED = $constEnum->getLOSSED();

		//--------------------------------セッション＆エラー管理---------------------------------------

		//DBに接続するためにDBInfoをインスタンス生成
		$dbInfo = new WorkManage\DBInfo();

		//DBInfo生成時（コンストラクター内でPDOインスタンスを生成してDBに接続している）
		//にエラーが起きたらfalse
		error($dbInfo, 'dbErrMes', 'index.php');

		//セッションにIDが入っいない(URL直打ちなど)場合、エラーが起きてfalse
		//index.phpへ遷移する
		$checkFlag = check($_SESSION['id']);
		error($checkFlag, 'screenErrMes', '../index.php');

		?>

		<div id="erroText">
			<p id="editErroText">入力に誤りがあります</p>
		</div>

		<?php


		//--------------------------------一般ユーザー（情報変更一部変更可）---------------------------------------


		if ($_SESSION['su'] == $USER) {

			//ユーザーIDを入れる場所
			$userList = $dbInfo->select($_SESSION['id']);

			//結果を正しく取得できていないときfalse
			error($userList, 'dbErrMes', '../index.php');
			// var_dump($userList);
			echo "<br>";

			foreach ($userList as $row => $value) {
				echo '<div class="h-adr">';
				echo '<form  action="infoproc/correction.php" method="post" 
				onsubmit="return editErroMess(editUserRegularExpression())">';
				echo '<table border="1" class="infoTable">';
				echo '<tr>';
				echo '<input type="hidden" name="id" value="', $value['id'], '">';
				echo '</tr>';

				echo '<tr><td class="infoTdLeft">名前</td>';
				echo '<td class="infoTdRight">';
				echo '<input type="text" name="name" value="', $value['name'], '">';
				echo '</td>';
				echo '</tr>';

				echo '<tr><td>フリガナ</td>';
				echo '<td>';
				echo '<input type="text" name="furi" value="', $value['furi'], '">';
				echo '</td>';
				echo '</tr>';

				echo '<tr><td>入社日</td>';
				echo '<td>';
				echo '<input type="date" name="enterdate" value="', $value['enterdate'], '">';
				echo '</td>';
				echo '</tr>';

				?>
				<tr><td>郵便番号</td>
					<td>
						<span class="p-country-name" style="display:none;">Japan</span>
						<input type="text" name="postno" class="p-postal-code" size="8" maxlength="8" value="<?php echo $value['postno'] ?>">
					</td>
				</tr>

				<tr><td>住所</td>
					<td>
						<input type="text" name="address" class="p-region p-locality p-street-address p-extended-address" value="<?php echo $value['address'] ?>">
					</td>
				</tr>

				<?php

				echo '<tr><td>電話番号</td>';
				echo '<td>';
				echo '<input type="text" name="tel" value="', $value['tel'], '">';
				echo '</td>';
				echo '</tr>';

				echo '<tr><td>生年月日</td>';
				echo '<td>';
				echo '<input type="date" name="birth" value="', $value['birth'], '">';
				echo '</td>';
				echo '</tr>';

				//ここから一般ユーザーは表示のみ ※保険関係と管理者権限情報は非表示

				//社会保険
				if ($value['si'] == $NOT_SUBSCRIBED) {
					echo '<input type="hidden" name="si" value="0">';
				} elseif ($value['si'] == $SUBSCRIBED) {
					echo '<input type="hidden" name="si" value="1">';
				}

				//雇用保険
				if ($value['ei'] == $NOT_SUBSCRIBED) {
					echo '<input type="hidden" name="ei" value="0">';
				} elseif ($value['ei'] == $SUBSCRIBED) {
					echo '<input type="hidden" name="ei" value="1">';
				}

				//雇用保険番号
				if ($value['eino'] == null) {
					echo '<input type="hidden" name="eino">';
				} else {
					echo '<input type="hidden" name="eino" value="', $value['eino'], '">';
				}



				echo '<tr><td>退職日</td>';
				echo '<td>';
				if ($value['retire'] == null) {
					echo '<input type="hidden" name="retire">';
					echo '---';
				} else {
					echo '<input type="hidden" name="retire" value="', $value['retire'], '">';
					echo $value['retire'];
				}

				echo '</td>';
				echo '</tr>';


				//社会保険損失
				if ($value['si_loss'] == null) {
					echo '<input type="hidden" name="si_loss">';
				} elseif ($value['si_loss'] == $NOT_LOSSED) {
					echo '<input type="hidden" name="si_loss" value="0">';
				} elseif ($value['si_loss'] == $LOSSED) {
					echo '<input type="hidden" name="si_loss" value="1">';
				}

				//雇用保険損失
				if ($value['ei_loss'] == null) {
					echo '<input type="hidden" name="ei_loss">';
				} elseif ($value['ei_loss'] == $NOT_LOSSED) {
					echo '<input type="hidden" name="ei_loss" value="0">';
				} elseif ($value['ei_loss'] == $LOSSED) {
					echo '<input type="hidden" name="ei_loss" value="1">';
				}
				echo "</table>";

				echo '<p></p><table border="1" class="infoTable">';
				echo '<tr><td class="infoTdLeft">デスクネッツパスワード</td>';
				echo '<td class="infoTdRight">';
				echo '<input type="hidden" name="desknetspass" value="', $value['desknetspass'], '">';
				echo $value['desknetspass'];
				echo '</td>';
				echo '</tr>';
				echo "</table>";

				echo '<p></p><table border="1" class="infoTable">';
				echo '<tr><td class="infoTdLeft">共有　ユーザー名</td>';
				echo '<td class="infoTdRight">';
				echo '<input type="hidden" name="shareuser" value="', $value['shareuser'], '">';
				echo $value['shareuser'];
				echo '</td>';
				echo '</tr>';

				echo '<tr><td>共有　パスワード</td>';
				echo '<td>';
				echo '<input type="hidden" name="sharepass" value="', $value['sharepass'], '">';
				echo $value['sharepass'];
				echo '</td>';
				echo '</tr>';
				echo "</table>";

				echo '<p></p><table border="1" class="infoTable">';
				echo '<tr><td class="infoTdLeft">メールアドレス</td>';
				echo '<td class="infoTdRight">';
				echo '<input type="hidden" name="emailaddress" value="', $value['emailaddress'], '">';
				echo $value['emailaddress'];
				echo '</td>';
				echo '</tr>';

				echo '<tr><td>メールパスワード</td>';
				echo '<td>';
				echo '<input type="hidden" name="emailpass" value="', $value['emailpass'], '">';
				echo $value['emailpass'];
				echo '</td>';
				echo '</tr>';
				echo "</table>";

				//旧・社員情報管理者権限
				echo '<input type="hidden" name="su_old" value="', $value['su'], '">';

				echo '<input type="hidden" name="su" value="', $value['su'], '">';

				echo '<p></p><table border="1" class="infoTable">';
				echo '<tr><td class="infoTdLeft">ログインパスワード</td>';
				echo '<td class="infoTdRight">';
				echo '<input type="hidden" name="password" value="', $value['password'], '">';
				echo $value['password'];
				echo '</td>';
				echo '</tr>';
				echo "</table>";
			}

			
			echo '<input type="submit" value="修正" class="editButton">';
			echo '</form> ';
			

			//------------------------------管理者ユーザー（全情報変更可能）---------------------------

		} elseif ($_SESSION['su'] == $ADMIN) {

			//ユーザーIDを入れる場所
			$userList = $dbInfo->select($_GET['id']);

			//結果を正しく取得できていないときfalse

			error($userList, 'dbErrMes', '../index.php');

			foreach ($userList as $row => $value) {
				echo '<div class="h-adr">';
				echo '<form  action="infoproc/correction.php" method="post" 
				onsubmit="return editErroMess(editAdminRegularExpression())">';
				//社員ID
				echo '<p></p><table border="1" class="infoTable">';
				echo '<tr>';
				echo '<input type="hidden" name="id" value="', $value['id'], '">';
				echo '</tr>';

				echo '<tr><td class="infoTdLeft">名前</td>';
				echo '<td class="infoTdRight">';
				echo '<input type="text" name="name" value="', $value['name'], '">';
				echo '</td>';
				echo '</tr>';

				echo '<tr><td>フリガナ</td>';
				echo '<td>';
				echo '<input type="text" name="furi" value="', $value['furi'], '">';
				echo '</td>';
				echo '</tr>';

				echo '<tr><td>入社日</td>';
				echo '<td>';
				echo '<input type="date" name="enterdate" value="', $value['enterdate'], '">';
				echo '</td>';
				echo '</tr>';

				?>
				<tr><td>郵便番号</td>
					<td>
						<span class="p-country-name" style="display:none;">Japan</span>
						<input type="text" name="postno" class="p-postal-code" size="8" maxlength="8" value="<?php echo $value['postno'] ?>">
					</td>
				</tr>

				<tr><td>住所</td>
					<td>
						<input type="text" name="address" class="p-region p-locality p-street-address p-extended-address" value="<?php echo $value['address'] ?>">
					</td>
				</tr>

				<?php
				echo '<tr><td>電話番号</td>';
				echo '<td>';
				echo '<input type="text" name="tel" value="', $value['tel'], '">';
				echo '</td>';
				echo '</tr>';

				echo '<tr><td>生年月日</td>';
				echo '<td>';
				echo '<input type="date" name="birth" value="', $value['birth'], '">';
				echo '</td>';
				echo '</tr>';


				if ($value['si'] == $NOT_SUBSCRIBED) {
					echo '<tr><td>社会保険</td>';
					echo '<td>';
					echo '<input type="checkbox" name="si" value="1">　取得すみ';
					echo '</td>';
					echo '</tr>';
				} elseif ($value['si'] == $SUBSCRIBED) {
					echo '<tr><td>社会保険</td>';
					echo '<td>';
					echo '<input type="checkbox" name="si" value="1" checked>　取得すみ';
					echo '</td>';
					echo '</tr>';
				}

				if ($value['ei'] == $NOT_SUBSCRIBED) {
					echo '<tr><td>雇用保険</td>';
					echo '<td>';
					echo '<input type="checkbox" name="ei" value="1">　取得すみ';
					echo '</td>';
					echo '</tr>';
				} elseif ($value['ei'] == $SUBSCRIBED) {
					echo '<tr><td>雇用保険</td>';
					echo '<td>';
					echo '<input type="checkbox" name="ei" value="1" checked>　取得すみ';
					echo '</td>';
					echo '</tr>';
				}

				echo '<tr><td>雇用保険被保険者番号</td>';
				echo '<td>';
				echo '<input type="text" name="eino" value="', $value['eino'], '">';
				echo '</td>';
				echo '</tr>';

				echo '<tr><td>退職日</td>';
				echo '<td>';
				echo '<input type="date" name="retire" value="', $value['retire'], '">';
				echo '</td>';
				echo '</tr>';

				if ($value['si_loss'] == $NOT_LOSSED || $value['si_loss'] == null) {
					echo '<tr><td>社会保険損失</td>';
					echo '<td>';
					echo '<input type="checkbox" name="si_loss" value="1">　損失すみ';
					echo '</td>';
					echo '</tr>';
				} elseif ($value['si_loss'] == $LOSSED) {
					echo '<tr><td>社会保険損失</td>';
					echo '<td>';
					echo '<input type="checkbox" name="si_loss" value="1" checked>　損失すみ';
					echo '</td>';
					echo '</tr>';
				}


				if ($value['ei_loss'] == $NOT_LOSSED || $value['ei_loss'] == null) {
					echo '<tr><td>雇用保険損失</td>';
					echo '<td>';
					echo '<input type="checkbox" name="ei_loss" value="1">　損失すみ';
					echo '</td>';
					echo '</tr>';
				} elseif ($value['ei_loss'] == $LOSSED) {
					echo '<tr><td>雇用保険損失</td>';
					echo '<td>';
					echo '<input type="checkbox" name="ei_loss" value="1"checked>　損失すみ';
					echo '</td>';
					echo '</tr>';
				}
				echo "</table>";

				echo '<p></p><table border="1" class="infoTable">';
				echo '<tr><td class="infoTdLeft">デスクネッツパスワード</td>';
				echo '<td class="infoTdRight">';
				echo '<input type="text" name="desknetspass" value="', $value['desknetspass'], '">';
				echo '</td>';
				echo '</tr>';
				echo "</table>";

				echo '<p></p><table border="1" class="infoTable">';
				echo '<tr><td class="infoTdLeft">共有　ユーザー名</td>';
				echo '<td class="infoTdRight">';
				echo '<input type="text" name="shareuser" value="', $value['shareuser'], '">';
				echo '</td>';
				echo '</tr>';

				echo '<tr><td>共有　パスワード</td>';
				echo '<td>';
				echo '<input type="text" name="sharepass" value="', $value['sharepass'], '">';
				echo '</td>';
				echo '</tr>';
				echo "</table>";

				echo '<p></p><table border="1" class="infoTable">';
				echo '<tr><td class="infoTdLeft">メールアドレス</td>';
				echo '<td class="infoTdRight">';
				echo '<input type="email" name="emailaddress" value="', $value['emailaddress'], '">';
				echo '</td>';
				echo '</tr>';

				echo '<tr><td>メールパスワード</td>';
				echo '<td>';
				echo '<input type="text" name="emailpass" value="', $value['emailpass'], '">';
				echo '</td>';
				echo '</tr>';
				echo "</table>";

				//旧・社員情報管理者権限
				echo '<input type="hidden" name="su_old" value="', $value['su'], '">';

				echo '<p></p><table border="1" class="infoTable">';
				echo '<tr><td class="infoTdLeft">社員情報管理権限</td>';
				echo '<td class="infoTdRight">';
				if ($value['su'] == $USER) {
					echo '<input type="radio" name="su" value="0" checked>';
					echo '一般ユーザー';
					echo '<input type="radio" name="su" value="1">';
					echo '管理ユーザー';
				} elseif ($value['su'] == $ADMIN) {
					echo '<input type="radio" name="su" value="0">';
					echo '一般ユーザー';
					echo '<input type="radio" name="su" value="1" checked>';
					echo '管理ユーザー';
				}
				echo '</td>';
				echo '</tr>';
				echo "</table>";

				echo '<p></p><table border="1" class="infoTable">';
				echo '<tr><td class="infoTdLeft">ログインパスワード</td>';
				echo '<td class="infoTdRight">';
				echo '<input type="text" name="password" value="', $value['password'], '">';
				echo '</td>';
				echo '</tr>';
				echo '</table>';
			}
			echo '<input type="submit" value="修正" class="editButton">';
			echo '</form> ';


			echo '</form> ';
			
		}
		
		echo '</div>';
		if($_SESSION['su'] == $USER){
			echo '<div class="infoUserHref">';
			echo '<a href="info.php">戻る</a>';
			echo '<a href="infoproc/logout.php">ログアウト</a>';
			echo "</div>";
		}elseif ($_SESSION['su'] == $ADMIN) {
			echo '<div class="infoAdminHref">';
			echo '<a href="list.php">一覧</a>';
			echo '<a href="infoproc/logout.php">ログアウト</a>';
			echo '<a href="delete.php?id=',$_GET["id"],'">削除</a>';
			echo "</div>";
		}
		
		?>
	
	</main>
	<script src="infoproc/RegularExpression.js" defer></script>
	<script src="https://yubinbango.github.io/yubinbango/yubinbango.js" charset="UTF-8"></script>
</body>
</html>