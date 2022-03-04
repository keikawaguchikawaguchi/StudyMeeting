<?php session_start(); ?>

<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ログイン画面</title>
    <link rel="stylesheet" href="infoproc/style.css">
    <?php require 'infoproc/Const.php'; ?>
    <?php require 'infoproc/DBInfo.php'; ?>
    <?php require 'infoproc/function.php'; ?>

</head>

<body>

    <form action="infoproc/login.php" method="post" style="text-align: center;">
        <p>ログインID <input type="text" name="login_id" autocomplete="on"></p>
        <p>パスワード <input type="password" name="password" autocomplete="off"></p>
        <input type="submit" value="ログイン">
    </form>

    <p></p>
    <div style="text-align: center;">
        <?php

        $errMes = getError();
        if (isset($errMes)) {
            echo $errMes;
        }

        ?>
    </div>
</body>

</html>