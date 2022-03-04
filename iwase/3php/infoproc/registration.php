<?php
session_start();
require 'Const.php';

require 'DBInfo.php';

require 'function.php';

//DBに接続するためにDBInfoをインスタンス生成
$dbInfo = new WorkManage\DBInfo();

// 管理者か確認（意図しない画面推移を防ぐため）
$masterFlag = checkSu($_SESSION['su']);
error($masterFlag, 'screenErrMes', '../index.php');

if (isset($_POST['login_id']) && isset($_POST['password'])) {
    if ($_POST['login_id'] === null || $_POST['password'] === null) {
        error(false, 'notIsset', '../new.php');
    } else {
        $login_id = $_POST['login_id'];
        $password = $_POST['password'];
    }
} else {
    error(false, 'notIsset', '../new.php');
}

echo $login_id;
echo $password;

$flagRegularExpression = regularExpression($login_id, $password);
error($flagRegularExpression, 'screenErrMes', '../new.php');

$userDuplication = $dbInfo->duplicationCheck($login_id, $password);

error($userDuplication, 'userDuplication', '../new.php');

$newUserInsert = $dbInfo->insert($login_id, $password);
error($newUserInsert, 'dbErrMes', '../new.php');
header("Location: ../list.php");
exit();
