<?php
session_start();
require 'Const.php';

require 'DBInfo.php';

require 'function.php';

//DBに接続するためにDBInfoをインスタンス生成
$dbInfo = new WorkManage\DBInfo();

//DBのカラムをすべて取得
//カラムのコメントも取得
$columns = $dbInfo->getColumn();

error($columns, 'dbErrMes', '../index.php');

$constEnum = new WorkManage\ConstEnum();

var_dump($_POST['li_ipdate']);
if ($_POST['li_ipdate'] == '') {
    echo '空白';
}
if (isset($_POST['li_ipdate'])) {
    echo 'postIsset';
}

$a = '';
var_dump($a);
if (isset($a)) {
    echo 'isset';
}

$ary = array();

for ($i = 22; $i < 49; $i++) {
    //nullか初期化してない時以外
    if (isset($_POST[$columns[$i]['Field']])) {
        //onだった場合１を代入
        if ($_POST[$columns[$i]['Field']] == 'on') {

            $value = $constEnum->getACQUISITION();

            //空文字の場合null
        } else if ($_POST[$columns[$i]['Field']] === '') {
            if ($i == 48) {
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


var_dump($ary);
