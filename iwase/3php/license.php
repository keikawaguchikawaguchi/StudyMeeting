<?php
require 'head.php';

$constEnum = new WorkManage\ConstEnum();
//セッションにIDがはいっているかチェック入っていなければindex.phpにもどる
$sessionFlag = check($_SESSION['id']);
error($sessionFlag, 'screenErrMes', 'index.php');

$id = $_SESSION['id'];

//管理者ならゲットでIDを受け取る
if ($_SESSION['su'] == $constEnum->getADMIN()) {
    $id = $_GET['id'];
}

//DBに接続するためにDBInfoをインスタンス生成
$dbInfo = new WorkManage\DBInfo();

//DBからgetで指定された社員情報を取得
$userList = $dbInfo->select($id);

error($userList, 'dbErrMes', 'index.php');

//DBのカラムをすべて取得
//カラムのコメントも取得
$columns = $dbInfo->getColumn();

error($columns, 'dbErrMes', 'index.php');

?>

<title>資格取得情報</title>
</head>

<body>
    <main class="licenseMain">
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
            <p id="jsErro">チェックボックスか日付どちらかが入力されていません。</p>
            <p id="jsErroLength">その他資格情報が文字数オーバーです。</p>
        </div>


        <form action="infoproc/licensecorrection.php" method="POST" onsubmit="return licenseRECheck()">
            <input type="hidden" name="id" value="<?php echo $id ?>">
            <table border="1" style="border: 1px solid black; border-collapse: collapse;">

                <tr>
                    <td style="border-top:1px solid white; border-left:1px solid white;"></td>
                    <td>資格名</td>
                    <td>取得日時</td>
                </tr>

                <?php

                for ($i = 22; $i <= 34; $i++) {
                    echo '<tr>';
                    echo '<td>';
                    if ($userList[0][$columns[$i]['Field']] == $constEnum->getUN_ACQUISIION()) {
                        echo '<input type="checkbox" name="', $columns[$i]['Field'], '">';
                    } elseif ($userList[0][$columns[$i]['Field']] == $constEnum->getACQUISITION()) {
                        echo '<input type="checkbox" name="', $columns[$i]['Field'], '" checked>';
                    }
                    echo '</td>';
                    echo '<td>';
                    echo $columns[$i]['Comment'];
                    echo '</td>';
                    echo '<td>';
                    echo '<input type="date" name="', $columns[$i + 13]['Field'],
                    '" value="', $userList[0][$columns[$i + 13]['Field']], '">';
                    echo '</td>';
                    echo '</tr>';
                }

                ?>

            </table>
            <p>その他資格情報</p>
            <input type="text" name="li_other" value="<?php echo $userList[0][$columns[48]['Field']] ?>"> <br>

            <br>
            <div class="licenseHrefButton">
                <?php
                if ($_SESSION['su'] == $constEnum->getADMIN()) {
                    echo '<a href="list.php">一覧</a>';
                } elseif ($_SESSION['su'] == $constEnum->getUSER()) {
                    echo '<a href="info.php">戻る</a>';
                }
                ?>
                <a href="infoproc/logout.php">ログアウト</a>
                <input type="submit" value="修正">
            </form>
        </div>

    </main>
    <script src="infoproc/RegularExpression.js" defer></script>
</body>

</html>