<?php

//エラーフラグとエラーの種類、遷移先URLをもらってフラグがfalseならエラー先に戻る
function error($flag, $errKey, $url){
    $erroMes = [
        'dbErrMes' => 'データベース関連のエラーが発生しました。',
        'screenErrMes' => '意図しない画面推移です。',
        'notIsset' => '値が正しく入力されていません。',
        'notFoundUser' => '指定した社員は存在しません。',
        'userDuplication' => '入力されたIDとパスワードは既に登録済みです。',
        'notWrite' => 'CSV出力に失敗しました。'
    ];

    //DBエラー時にセッションにエラーメッセージを入れエラー画面に移動
    if ($flag === false) {
        //配列の中から数字に対応するエラーメッセージをセッションに代入
        $_SESSION['err'] = $erroMes[$errKey];
        //エラー画面に移動
        header("Location: $url");
        //これ以降の処理がされないようにする
        exit();
    }
}


//管理者権限の確認
function checkSu($basePath){
    $constEnum = new WorkManage\ConstEnum();
    if ($basePath == $constEnum->getADMIN()) {
        return true;
    } else {
        return false;
    }
}

//エラーメッセージをとりだす
function getError(){
    if (isset($_SESSION['err'])) {
        $errMes = $_SESSION['err'];
        unset($_SESSION['err']);
        return $errMes;
    }
}

//年齢　引数はdate型の誕生日
function age($birth){
    if ($birth === null) {
        return '';
    } else {
        // 現在日付
        $now = date('Ymd');


        // 誕生日
        $birth = str_replace("-", "", $birth);

        // 年齢
        $age = floor(($now - $birth) / 10000);
        return $age;
    }
}

//勤続年月　引数はdate型の入社日
function interval($enterdate){
    if ($enterdate === null) {
        return "";
    } else {
        $now = new DateTime('now');
        $enterdate = DateTime::createFromFormat('Y-m-d', $enterdate);
        $yearOfService = $enterdate->diff($now);
        $yearOfService = $yearOfService->format('%y年%mヵ月');
        return $yearOfService;
    }
}


//社員情報一覧用　DBから配列にしたものを整える
function prepareAllList($allList, $Columns, $flag){

    $constEnum = new WorkManage\ConstEnum();
    $csvHeader = array();
    $allListId = array();
    $prepareAllList = array();

    foreach ($Columns as $key => $value) {
        if ($key > 0 && $key < 20) {
            array_push($csvHeader, $value['Comment']);

            if ($value['Comment'] === '入社日') {
                array_push($csvHeader, '勤続年数');
            }

            if ($value['Comment'] === '生年月日') {
                array_push($csvHeader, '年齢');
            }
        }
    }

    $allListId[0] = $csvHeader;
    $prepareAllList[] = $csvHeader;


    foreach ($allList as $key1 => $value1) {
        $allListRow = array();
        if ($value1['su'] != $constEnum->getNON_USER()) {
            foreach ($Columns as $key2 => $value2) {

                if ($key2 < 20) {


                    if ($key2 === 0) {
                        $id = $value1[$value2['Field']];
                    } else {
                        if ($value2['Field'] === 'birth') {
                            $birth = $value1[$value2['Field']];

                            array_push($allListRow, str_replace("-", "/", $birth));
                        } elseif ($value2['Field'] === 'enterdate') {

                            $enterdate = $value1[$value2['Field']];
                            array_push($allListRow, str_replace("-", "/", $enterdate));
                        } elseif ($value2['Field'] === 'si') {

                            if ($value1[$value2['Field']] == $constEnum->getNOT_SUBSCRIBED()) {
                                $si = '未加入';
                            } elseif ($value1[$value2['Field']] == $constEnum->getSUBSCRIBED()) {
                                $si = '加入済み';
                            }

                            array_push($allListRow, $si);
                        } elseif ($value2['Field'] === 'ei') {

                            if ($value1[$value2['Field']] == $constEnum->getNOT_SUBSCRIBED()) {
                                $ei = '未加入';
                            } elseif ($value1[$value2['Field']] == $constEnum->getSUBSCRIBED()) {
                                $ei = '加入済み';
                            }

                            array_push($allListRow, $ei);
                        } elseif ($value2['Field'] === 'si_loss') {

                            if ($value1[$value2['Field']] == $constEnum->getNOT_LOSSED()) {
                                $si = '未損失';
                            } elseif ($value1[$value2['Field']] == $constEnum->getLOSSED()) {
                                $si = '損失済み';
                            }

                            array_push($allListRow, $si);
                        } elseif ($value2['Field'] === 'ei_loss') {

                            if ($value1[$value2['Field']] == $constEnum->getNOT_LOSSED()) {
                                $ei = '未損失';
                            } elseif ($value1[$value2['Field']] == $constEnum->getLOSSED()) {
                                $ei = '損失済み';
                            }

                            array_push($allListRow, $ei);
                        } elseif ($value2['Field'] === 'tel') {
                            $tel = $value1[$value2['Field']];
                            $tel = '="' . $tel . '"';

                            array_push($allListRow, $tel);
                        } else {
                            array_push($allListRow, $value1[$value2['Field']]);
                        }

                        if ($value2['Comment'] === '入社日') {
                            array_push($allListRow, interval($enterdate));
                        }

                        if ($value2['Comment'] === '生年月日') {
                            array_push($allListRow, age($birth));
                        }
                    }
                }
            }
            $allListId[$id] = $allListRow;

            $prepareAllList[] = $allListRow;
        }
    }

    if ($flag) {
        return $prepareAllList;
    } else {

        return $allListId;
    }
}

//新規登録時の内部正規表現（半角英数字＆文字数）
function regularExpression($login_id, $password){
    $login_idCheck = preg_match('/^[0-9a-zA-Z]+$/', $login_id);
    $passwordCheck = preg_match('/^[0-9a-zA-Z]+$/', $password);
    $login_idLength = mb_strlen($login_id) < 10;
    $passwordLength = mb_strlen($password) < 10;

    if ($login_idCheck && $passwordCheck) {
        $flagCheck = true;
    } else {
        $flagCheck = false;
    }

    if ($login_idLength && $passwordLength) {
        $flagLength = true;
    } else {
        $flagLength = false;
    }

    if ($flagCheck && $flagLength) {
        return true;
    } else {
        return false;
    }
}





















































































//~300　岩瀬

//302~502 堀木

//ログインIDがセッションに入っているかの確認
function check($basePath){
    return isset($basePath);
}

//テキストボックスの値に空白スペース(半角全角ともに)があった場合 false
function emptyCheck($text){
    if (empty(trim(mb_convert_kana($text, "s", 'UTF-8')))) {
        return false;
    } else {
        return true;
    }
}

//社員情報の内部正規表現（半角英数字＆文字数）
//一般社員用
function infoUserRegularExpression(
    $name, 
    $furi, 
    $postno,
    $tel,
    $address) {

    //※テキストボックス空白禁止

    //名前 100文字以内
    $nameLength = mb_strlen($name) <= 100; 

    $nameEmpty = emptyCheck($name);

    //フリガナ 100文字以内
    $furiLength = mb_strlen($furi) <= 100; 
    $furiEmpty = emptyCheck($furi);

    //郵便番号 3桁-4桁  数字のみ
    $postnoCheck = preg_match('/^[0-9]{3}-[0-9]{4}+$/', $postno);
    $postnoEmpty = emptyCheck($postno);

    //住所 300文字以内
    $addressLength = mb_strlen($address) <= 300; 
    $addressEmpty = emptyCheck($address);

    //電話番号 10-20文字 数字のみ
    $telFormat = telFormat($tel);

    if($telFormat == $tel){
        $telCheck = false;
    }else{
        $telCheck = true;
    }

    $telEmpty = emptyCheck($tel);


    if (
        $nameLength && 
        $nameEmpty && 
        $furiLength && 
        $furiEmpty && 
        $postnoCheck && 
        $postnoEmpty &&
        $addressLength && 
        $addressEmpty && 
        $telCheck &&
        $telEmpty) {

        return true;
    } else {

        return false;
    }
}


//社員情報の内部正規表現（半角英数字＆文字数）
//管理者用
function infoAdminRegularExpression(
    $name, 
    $furi, 
    $postno,
    $tel, 
    $eino, 
    $address, 
    $desknetspass, 
    $shareuser, 
    $sharepass, 
    $emailaddress, 
    $emailpass, 
    $password) {

    //※テキストボックス空白禁止

    //名前 100文字以内
    $nameLength = mb_strlen($name) <= 100; 

    $nameEmpty = emptyCheck($name);


    //フリガナ 100文字以内
    $furiLength = mb_strlen($furi) <= 100; 
    $furiEmpty = emptyCheck($furi);

    //郵便番号 3桁-4桁  数字のみ
    $postnoCheck = preg_match('/^[0-9]{3}-[0-9]{4}+$/', $postno);


    //住所 300文字以内
    $addressLength = mb_strlen($address) <= 300; 
    $addressEmpty = emptyCheck($address);

    //電話番号 10-20文字 数字のみ
    $telFormat = telFormat($tel);
    
    if($telFormat == $tel){
        $telCheck = false;
    }else{
        $telCheck = true;
    }

    $telEmpty = emptyCheck($tel);

    //雇用被保険者番号 4桁-6桁-1桁 数字のみ
    $einoCheck = preg_match('/^[0-9]{4}-[0-9]{6}-[0-9]{1}+$/', $eino);

    
    //デスクネッツパスワード 100文字以内 英数字記号
    $desknetspassCheck = preg_match('/^[a-zA-Z0-9!-~]{1,100}+$/', $desknetspass);

    //共有ユーザー 100文字以内 英数字記号    
    $shareuserCheck = preg_match('/^[a-zA-Z0-9!-~]{1,100}+$/', $shareuser);   

    //共有パスワード 100文字以内 英数字記号    
    $sharepassCheck = preg_match('/^[a-zA-Z0-9!-~]{1,100}+$/', $sharepass);    

    //メールアドレス 500文字以内 英数字記号 メールアドレス形式 ドメイン名 @intecs.ne.jp
    $emailaddressCheck = preg_match('/^[a-zA-Z0-9_+-]+(.[a-zA-Z0-9_+-]+)*@intecs.ne.jp+$/', $emailaddress);
    
    //メールパスワード 100文字以内 英数字のみ
    $emailpassCheck = preg_match('/^[a-zA-Z0-9]{1,100}+$/', $emailpass);

    //ログインパスワード 100文字以内 英数字のみ
    $passwordCheck = preg_match('/^[a-zA-Z0-9]{1,100}+$/', $password);


    if (
        $nameLength &&
        $nameEmpty &&
        $furiLength &&
        $furiEmpty &&
        $postnoCheck && 
        $addressLength &&
        $addressEmpty &&
        $telCheck &&
        $telEmpty &&
        $einoCheck &&
        $desknetspassCheck &&
        $shareuserCheck &&
        $sharepassCheck &&
        $emailaddressCheck &&
        $emailpassCheck &&
        $passwordCheck) {

        return true;
    } else {

        return false;
    }
}












































//302~502 堀木

//504~704 兼松

//社員情報管理権限を文字列で返却する。
function su($su){
    $constEnum = new WorkManage\ConstEnum();
    if ($su == $constEnum->getADMIN()) {
        return "管理ユーザ";
    } elseif ($su == $constEnum->getUSER()) {
        return "一般ユーザ";
    } else {
        return "ユーザ削除";
    }
}

//電話番号にハイフンを入れて表示させる。
//https://mugenweb-note.com/web/php/telephone-number-replace
function telFormat($tel){
    //ハイフンを一旦なくしておく。
    $tel = str_replace("-", "", $tel);

    $category = array(
        "normal" => "/^0[^346]\d{8}$/",
        "mobile" => "/^0[0-9]{10}$/",
        "tokyo" => "/^0[346][0-9]{8}$/",
        "none"   => "/^[0-9]{7}$/",
    );

    $pattern = array(
        "normal" => "/(\d{4})(\d{2})(\d{4})/",
        "mobile" => "/(\d{3})(\d{4})(\d{4})/",
        "tokyo"  => "/(\d{2})(\d{4})(\d{4})/",
        "none"   => "/(\d{3})(\d{4})/",
    );
    
    $rep = array(
        "normal" => "$1-$2-$3",
        "none"   => "$1-$2",
    );
    //携帯
    if (preg_match($category['mobile'], $tel)) {
        $result = preg_replace($pattern['mobile'], $rep['normal'], $tel);
    }

    //市外局番2桁
    elseif (preg_match($category['tokyo'], $tel)) {
        $result = preg_replace($pattern['tokyo'], $rep['normal'], $tel);
    }

    //市外局番4桁
    elseif (preg_match($category['normal'], $tel)) {
        $result = preg_replace($pattern['normal'], $rep['normal'], $tel);
    }

    //市外局番なしなら
    elseif (preg_match($category['none'], $tel)) {
        $result = preg_replace($pattern['none'], $rep['none'], $tel);
    }

    //その他なら
    else {
        $result = $tel;
    }
    return $result;
}

function licenseRegularExpression($ary, $columns){
    $constEnum = new WorkManage\ConstEnum();
    $now = new \DateTime('now');

    for ($i = 35; $i <= 47; $i++) {
        $aryDate = $ary[$columns[$i]['Field']];

        if ($aryDate !== null) {
            list($year, $month, $day) = explode('-',  $aryDate);
            if (checkdate($month, $day, $year) === false) {
                return false;
            }

            $aryDate = \DateTime::createFromFormat('Y-m-d',  $aryDate);
            $diff = $aryDate->diff($now);
            if ($diff->invert === 1) {
                return false;
            }
        }
    }

    for ($i = 22; $i < 36; $i++) {
        if ($i === 35) {
            if (mb_strlen($ary[$columns[$i + 13]['Field']]) < 500) {
                return true;
            } else {
                return false;
            }
        }
        if (
            $ary[$columns[$i]['Field']] === $constEnum->getUN_ACQUISIION()
            && isset($ary[$columns[$i + 13]['Field']])
        ) {
            return false;
        } elseif (
            $ary[$columns[$i]['Field']] === $constEnum->getACQUISITION()
            && $ary[$columns[$i + 13]['Field']] === null
        ) {
            return false;
        }
    }
    return true;
}


























































































//504~704 兼松