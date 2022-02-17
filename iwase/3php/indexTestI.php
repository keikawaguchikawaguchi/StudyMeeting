<?php session_start(); ?>

<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ログイン画面</title>
    <link rel="stylesheet" href="css/style.css">
    <?php require 'Class/Const.php'; ?>
    <?php require 'Class/DBInfo.php'; ?>
    <?php require 'Function/function.php'; ?>

</head>

<body>
    <?php


    $errMes = getError();
    if (isset($errMes)) {
        echo $errMes;
    }

    if (isset($_SESSION['id'])) {
        echo $_SESSION['id'];
    }

    ?>
    <form action="user/login.php" method="post">
        <table>
            <tr>
                <td>ログインID</td>
                <td><input type="text" name="login_id"></td>
            </tr>
            <tr>
                <td>パスワード</td>
                <td><input type="password" name="password"></td>
            </tr>
        </table>
        <input type="submit" value="ログイン">
    </form>


    <form action="master/list.php" method="post">

        <input type="submit" value="管理者画面の社員一覧">
    </form>


</body>

</html>