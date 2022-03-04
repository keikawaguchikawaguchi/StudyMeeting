 <?php
    session_start();
    require 'Const.php';

    require 'DBInfo.php';

    require 'function.php';

    //入力されたログインIDとパスワードを取得する。

    if (isset($_POST['login_id']) && isset($_POST['password'])) {
        if ($_POST['login_id'] === null || $_POST['password'] === null) {

            error(false, 'notIsset', '../index.php');
        } else {
            $login_id = $_POST['login_id'];
            $password = $_POST['password'];
        }
    } else {
        error(false, 'notIsset', '../index.php');
    }
    //入力されたログインID、パスワードが正しいか判断する。

    //DBに接続するためにDBInfoをインスタンス生成
    $dbInfo = new WorkManage\DBInfo();

    //引数で指定されたログインID及びパスワードに一致するレコードがあるかを確認する。
    //一致する場合は社員IDと権限を返す。一致しない場合はNULLを返す。

    $loginInfo = $dbInfo->infoCheck($login_id, $password);

    error($loginInfo, 'dbErrMes', '../index.php');

    if (is_null($loginInfo)) {
        //infoCheckで返ってきた値がNULLの場合は
        //エラーメッセージをセッションerrに設定し、ログイン画面へ遷移する。
        error(false, 'notFoundUser', '../index.php');
    } else {
        //infoCheckで返ってきた値がNULLでない場合は
        //社員IDをセッションidに、権限をセッションsuに設定する。
        $_SESSION['id'] = $loginInfo[0]['id'];
        $_SESSION['su'] = $loginInfo[0]['su'];
    }


    // function suを使う。

    if (su($_SESSION['su']) == "管理ユーザ") {
        //管理ユーザはlist.phpに遷移する
        header('Location: ../list.php');
        exit();
    } elseif (Su($_SESSION['su']) == "一般ユーザ") {
        //一般ユーザはinfo.phpに遷移する
        header('Location: ../info.php');
        exit();
    }
