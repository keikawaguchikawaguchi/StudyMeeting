<?php
session_start();

session_unset();

header('Location: ../index.php');
        //これ以降の処理がされないようにする
exit();
