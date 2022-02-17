<?php
session_start();
require 'Const.php';
require 'DBInfo.php';
require 'function.php';

//管理者か確認（意図しない画面推移を防ぐため）
$masterFlag = checkSu($_SESSION['su']);
error($masterFlag, 'screenErrMes', '../index.php');

//DBに接続するためにDBInfoをインスタンス生成
    $dbInfo = new WorkManage\DBInfo();

//idうけとり
$id = $_POST['id'];

//削除できたかどうかをbooleanでうけとる
$row = $dbInfo->delete($id);

if ($row == 1) {
    $deleteFlag = true;
} else {
    $deleteFlag = false;
}

if ($deleteFlag) {
    header("Location: ../list.php");
} else {
    $_SESSION['err'] = '削除に失敗しました。';
    header("Location: ../delete.php?id=$id");
}
