<?php
require 'head.php';

?>

<title>社員削除</title>
</head>

<body>
    <main class="infoMain">
        <?php


    //管理者か確認（意図しない画面推移を防ぐため）
        $masterFlag = checkSu($_SESSION['su']);
        error($masterFlag, 'screenErrMes', 'index.php');

    //DBに接続するためにDBInfoをインスタンス生成
        $dbInfo = new WorkManage\DBInfo();


   //セッションにIDがはいっているかチェック入っていなければindex.phpにもどる
        $sessionFlag = check($_SESSION['id']);
        error($sessionFlag, 'screenErrMes', '../index.php');


        $id = $_GET['id'];


   //idに当てはまる社員の名前をとってくる
        $info = $dbInfo->select($id);
        error($info, 'dbErrMes', 'index.php');

        $name = $info[0]['name'];

    //エラーメッセージを表示
        $errMes = getError();
        if (isset($errMes)) {
            echo '<a id="newErrMes">', $errMes, '</a>';
            unset($_SESSION['err']);
        }
        ?>
        <!-- 画面表示 -->
        <p>社員　<?php echo $name ?>を削除しますか？</p>

        <form action="infoproc/deletion.php" method="post">
            <input type="hidden" name="id" value="<?php echo $id ?>">
            <input class="dleteButton" type="submit" value="削除する">
        </form>
        <div class="deleteHref">
            <a href="list.php">一覧</a><br><br>
            <a href="infoproc/logout.php">ログアウト</a>
            <a href="edit.php">修正</a>
        </div>
    </main>
</body>

</html>