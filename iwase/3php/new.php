<?php
require 'head.php';

//管理者か確認（意図しない画面推移を防ぐため）
$masterFlag = checkSu($_SESSION['su']);
error($masterFlag, 'screenErrMes', 'index.php');
?>

<script src="infoproc/RegularExpression.js" defer></script>

<title>社員一覧</title>
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
        <div id="erroText">
            <p id="chara">入力できるのは半角英数字のみです。</p>
            <p id="charaLength">入力数オーバーです。</p>
        </div>

        <form action="infoproc/registration.php" method="post" name="registration" onsubmit="return newRegularExpression()">

            ログインID<input type="text" name="login_id" autocomplete="off"><br><br>
            パスワード<input type="password" name="password" autocomplete="off"><br><br>
            <input type="submit" value="登録">
        </form>
        
        <div class="deleteHref">
            <a href="../user/logout.php">ログアウト</a>
            <a href="list.php">社員一覧</a>
        </div>

    </main>
</body>

</html>