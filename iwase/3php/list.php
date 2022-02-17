<?php

//htmlのテンプレートとfunction,DBInfo,cssを読み込み
require 'head.php'

?>

<title>社員一覧</title>
</head>

<body>
    <main class="listMain">

        <?php
    //管理者か確認（意図しない画面推移を防ぐため）
        $masterFlag = checkSu($_SESSION['su']);
        error($masterFlag, 'screenErrMes', 'index.php');

    //DBに接続するためにDBInfoをインスタンス生成
        $dbInfo = new WorkManage\DBInfo();


    //DBからすべての社員情報をとってくる
    //DB関連のエラーで戻り値false
        $allList = $dbInfo->selectAll();

    //$allListにfalseが入って入ればエラーで指定の画面に移動
        error($allList, 'dbErrMes', 'index.php');

    //DBのカラムをすべて取得
    //カラムのコメントも取得
    //DB関連のエラーで戻り値false
        $columns = $dbInfo->getColumn();

    //$columnsにfalseが入って入ればエラーで指定の画面に移動
        error($columns, 'dbErrMes', 'index.php');

        $constEnum = new WorkManage\ConstEnum();

        $allListId = prepareAllList($allList, $columns, $constEnum->getLIST());

        $errMes = getError();
        if (isset($errMes)) {
            echo $errMes;
            unset($_SESSION['err']);
        }

        ?>


        <table class="listTable" border="1" >

            <?php foreach ($allListId as $key => $value) : ?>
                <tr>
                    <?php for ($i = 0; $i < 21; $i++) : ?>
                        <?php
                        if ($key === 0) {
                            if($i === 0){
                                echo '<th class="fixed01" nowrap="nowrap">', $value[$i], '</th>';
                            }else{
                                echo '<th class="fixed02" nowrap="nowrap">', $value[$i], '</th>';
                            }
                        } else {

                            if ($i === 0) {
                                echo '<td class="fixed02" nowrap="nowrap"><a href="info.php?id=', $key, '">', $value[$i], '</a></td>';
                            } elseif ($i === 8) {
                                $tel = $value[$i];
                                $tel = str_replace('="', '', $tel);
                                $tel = str_replace('"', '', $tel);
                                echo '<td nowrap="nowrap">', $tel, '</td>';
                            } else {
                                echo '<td nowrap="nowrap">', $value[$i], '</td>';
                            }
                        }
                        ?>

                    <?php endfor ?>
                </tr>
            <?php endforeach ?>
        </table>
        <div class="infoAdminHref">
            <a href="infoproc/csv.php">CSVファイル出力</a>
            <a href="infoproc/logout.php">ログアウト</a>
            <a href="new.php">社員情報登録</a>
        </div>

    </main>
</body>

</html>