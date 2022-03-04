<?php

session_start();
require 'Const.php';
require 'DBInfo.php';
require 'function.php';

?>
<title>correction</title>
</head>

<main>


	<?php

	//------------------------------定数呼び出し-------------------

	$constEnum = new WorkManage\ConstEnum();
	//---社員情報管理者権限---
	//　一般ユーザー[0]
	$USER = $constEnum->getUSER();
	//管理者ユーザー[1]
	$ADMIN = $constEnum->getADMIN();
	//　退職ユーザー[2]
	$NOT_USER = $constEnum->getNON_USER();

	//　保険未加入[0]
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

	//セッションにIDが入っいない(URL直打ちなど)場合、エラーが起きてfalse
	//index.phpへ遷移する
	//セッションにIDがはいっているかチェック入っていなければindex.phpにもどる
	$sessionFlag = check($_SESSION['id']);
	error($sessionFlag, 'screenErrMes', '../index.php');

	//ユーザーIDを入れる場所
	$userList = $dbInfo->select($_SESSION['id']);

	//結果を正しく取得できていないときfalse
	error($userList, 'dbErrMes', '../index.php');

	//------------------------------一般ユーザー用受け取り---------------------------

	if ($_SESSION['su'] == $USER) {

		if ($_POST['eino'] == null) {
			$_POST['eino'] = null;
		}

		if ($_POST['retire'] == null) {
			$_POST['retire'] = null;
		}

		if ($_POST['si_loss'] == null) {
			$_POST['si_loss'] = null;
		}

		if ($_POST['ei_loss'] == null) {
			$_POST['ei_loss'] = null;
		}

		//受け取った値を連想配列に格納する

		$ary = array(
			'name' => $_POST['name'],
			'furi' => $_POST['furi'],
			'enterdate' => $_POST['enterdate'],
			'postno' => $_POST['postno'],
			'address' => $_POST['address'],
			'tel' => $_POST['tel'],
			'birth' => $_POST['birth'],
			'si' => $_POST['si'],
			'ei' => $_POST['ei'],
			'eino' => $_POST['eino'],
			'retire' => $_POST['retire'],
			'si_loss' => $_POST['si_loss'],
			'ei_loss' => $_POST['ei_loss'],
			'desknetspass' => $_POST['desknetspass'],
			'shareuser' => $_POST['shareuser'],
			'sharepass' => $_POST['sharepass'],
			'emailaddress' => $_POST['emailaddress'],
			'emailpass' => $_POST['emailpass'],
			'su' => $_POST['su'],
			'password' => $_POST['password'],
			'id' => $_POST['id']
		);
		

		//---------正規表現チェック

		$IREErroFlag = infoUserRegularExpression(
			$_POST['name'], 
			$_POST['furi'],
			$_POST['postno'], 
			$_POST['tel'],
			$_POST['address']);

		if($IREErroFlag === false){
		//配列の中から数字に対応するエラーメッセージをセッションに代入
			$_SESSION['err'] = '値が正しく入力されていません。';
        //edit.phpに移動
			header("Location: ../edit.php?id=$id");
        //これ以降の処理がされないようにする
			exit();
		}

		//---------データベース更新

		//結果を正しく取得できていないときfalse
		$dbErroFlag2 = $dbInfo->update($ary);

		error($dbErroFlag2, 'dbErrMes', '../index.php');
	}


	//------------------------------管理者ユーザー用受け取り---------------------------
	if ($_SESSION['su'] == $ADMIN) {

		//保険取得なし(チェックボックス未チェック)の場合 $NOT_SUBSCRIBEDを代入
		if (!isset($_POST['si'])) {
			$_POST['si'] = $NOT_SUBSCRIBED;
		}

		//保険取得なし(チェックボックス未チェック)の場合 $NOT_SUBSCRIBEDを代入
		if (!isset($_POST['ei'])) {
			$_POST['ei'] = $NOT_SUBSCRIBED;
		}

		//退職日なしの場合 NULLを代入
		if ($_POST['retire'] == null) {
			$_POST['retire'] = null;
		}

		//保険損失していない(チェックボックス未チェック)の場合 $NOT_LOSSEDを代入
		if (!isset($_POST['si_loss'])) {
			$_POST['si_loss'] = $NOT_LOSSED;
		}

		//保険損失していない(チェックボックス未チェック)の場合 $NOT_LOSSEDを代入
		if (!isset($_POST['ei_loss'])) {
			$_POST['ei_loss'] = $NOT_LOSSED;
		}

		//受け取った値を連想配列に格納する

		$ary = array(
			'name' => $_POST['name'],
			'furi' => $_POST['furi'],
			'enterdate' => $_POST['enterdate'],
			'postno' => $_POST['postno'],
			'address' => $_POST['address'],
			'tel' => $_POST['tel'],
			'birth' => $_POST['birth'],
			'si' => $_POST['si'],
			'ei' => $_POST['ei'],
			'eino' => $_POST['eino'],
			'retire' => $_POST['retire'],
			'si_loss' => $_POST['si_loss'],
			'ei_loss' => $_POST['ei_loss'],
			'desknetspass' => $_POST['desknetspass'],
			'shareuser' => $_POST['shareuser'],
			'sharepass' => $_POST['sharepass'],
			'emailaddress' => $_POST['emailaddress'],
			'emailpass' => $_POST['emailpass'],
			'su' => $_POST['su'],
			'password' => $_POST['password'],
			'id' => $_POST['id']
		);
		
		//管理者ユーザーが画面遷移時に使用
		$id = $_POST['id'];

		//---------正規表現チェック

		$IREErroFlag = infoAdminRegularExpression(
			$_POST['name'], 
			$_POST['furi'],
			$_POST['postno'],
			$_POST['tel'], 
			$_POST['eino'], 
			$_POST['address'], 
			$_POST['desknetspass'], 
			$_POST['shareuser'], 
			$_POST['sharepass'], 
			$_POST['emailaddress'], 
			$_POST['emailpass'], 
			$_POST['password']
		);

		if($IREErroFlag === false){
		//配列の中から数字に対応するエラーメッセージをセッションに代入
			$_SESSION['err'] = '値が正しく入力されていません。';
        //edit.phpに移動
			header("Location: ../edit.php?id=$id");
        //これ以降の処理がされないようにする
			exit();
		}

		//---------データベース更新

		//結果を正しく取得できていないときfalse
		$dbErroFlag2 = $dbInfo->update($ary);

		error($dbErroFlag2, 'dbErrMes', '../index.php');

	}

	header("Location: ../info.php?id=$id");
        //これ以降の処理がされないようにする
	exit();

	?>


</main>