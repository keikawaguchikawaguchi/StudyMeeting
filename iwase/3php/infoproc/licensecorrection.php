<?php
session_start();
require 'Const.php';

require 'DBInfo.php';

require 'function.php';

//セッションにIDがはいっているかチェック入っていなければindex.phpにもどる
$sessionFlag = check($_SESSION['id']);
error($sessionFlag, 'screenErrMes', '../index.php');

$id = $_POST['id'];

//DBに接続するためにDBInfoをインスタンス生成
$dbInfo = new WorkManage\DBInfo();

//DBのカラムをすべて取得
//カラムのコメントも取得
$columns = $dbInfo->getColumn();

error($columns, 'dbErrMes', '../index.php');

$constEnum = new WorkManage\ConstEnum();

$ary = array();

for ($i = 22; $i < 49; $i++) {
    //nullか初期化してない時以外
    if (isset($_POST[$columns[$i]['Field']])) {
        //onだった場合１を代入
        if ($_POST[$columns[$i]['Field']] === 'on') {

            $value = $constEnum->getACQUISITION();

            //空文字の場合null
        } elseif ($_POST[$columns[$i]['Field']] === '') {
            if ($i === 48) {
                $value = '';
            } else {
                $value = null;
            }
            //それ以外（今なら日付のとき）日付を代入
        } else {
            $value = $_POST[$columns[$i]['Field']];
        }

        //値がそもそもはいってなければ０
    } else {
        $value = $constEnum->getUN_ACQUISIION();
    }

    $ary = array_merge($ary, array($columns[$i]['Field'] => $value));
}

$ary = array_merge($ary, array('id' => $id));

$regularExpressionflag = licenseRegularExpression($ary, $columns);
error($regularExpressionflag, 'notIsset', "../license.php?id=$id");



  //updateメソッドを呼び出し、更新処理を行う。
$flag = $dbInfo->updateLicense($ary, $columns);
// var_dump($flag);

if ($flag === true) {
    //updateLicenseで返ってきた値がtrueの場合、資格保有情報修正画面へ遷移する。
    header("Location: ../license.php?id=$id");
} else {
    //updateLicenseで返ってきた値がfalseの場合
    //エラーメッセージをセッションerrに設定し、資格保有情報修正画面へ遷移する。
    error(false, 'notFoundUser', '../index.php');
    header('Location: ../license.php');
    exit();
}
