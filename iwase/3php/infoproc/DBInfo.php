<?php
//名前空間
namespace WorkManage {

    //javaでいうimportてきなものたぶん
    use Exception;

    use PDO;

    use PDOException;

    //クラス
    //流れ
    //DBに接続したいタイミングでこのクラスをインスタンス生成する。
    //その引数にmysql:host=localhost;dbname=odashi;charset=utf8','ojtUser', 'pass'
    //コンストラクタでメンバ変数$pdoにPDOクラスのインスタンスを代入
    //エラーが起きると勝手にキャッチしてくれるきがする。どっかでphpは自分でthrow文かかないと
    //catchしないってみたきが

    class DBInfo
    {
        //メンバ変数
        //PDOインスタンス用
        private PDO $pdo;


        //コンストラクタ
        //引数３つあるの注意！！理由わかんなかったら聞いて
        public function __construct(){

            try {

                //ここでPDOをインスタンス生成してメンバ変数に代入
                $this->pdo = new PDO('mysql:host=localhost;dbname=infodb;charset=utf8', 'ojtUser', 'pass');
            } catch (PDOException $e) {
                //エラーメッセージをセッションにセット
                $_SESSION['err'] = 'データベース関連のエラーが発生しました。';
                header("Location: ../index.php");
                //これ以降の処理がされないようにする
                exit();
            }
        }


        //!!ここからメソッド

        //カラム名取得
        //カラムのコメント取得       
        public function getColumn(){

            try {

                $sql = "show full columns from info";

                $rs = $this->pdo->query($sql);

                //すべての行を連想配列に直してる？たぶん
                $rs = $rs->fetchAll(PDO::FETCH_ASSOC);

                if (count($rs) === 0) {
                    throw new Exception();
                }

                return $rs;

                //例外が起きたらエラーフラグをfalseにする
            } catch (Exception $e) {
                return false;
            }
        }

        //DBの社員情報をすべて持ってくる
        public function selectAll(){
            try {
                //メンバ変数に代入したPDOのインスタンスのqueryメソッドを使ってDBから社員情報を取得
                $rs = $this->pdo->query('select * from info');

                $rs = $rs->fetchAll(PDO::FETCH_ASSOC);

                if (count($rs) == 0) {
                    throw new Exception();
                }

                return $rs;

                //例外が起きたらエラーフラグをfalseにする
            } catch (Exception $e) {
                return false;
            }
        }

        //引数で指定された社員IDの情報をinfoテーブルから取得し、返すメソッド
        public function select($id){
            try {
                $sql = $this->pdo->prepare('select * from info where id = ?');


                $sql->execute([$id]);

                $rs = $sql->fetchAll(PDO::FETCH_ASSOC);

                if (count($rs) === 0) {
                    throw new Exception();
                }

                return $rs;
            } catch (Exception $e) {
                return false;
            }
        }


        //registration ID　Password重複チェック
        public function duplicationCheck($login_id, $password){
            try {
                $sql = $this->pdo->prepare('select * from info where login_id = ? and password = ?');


                $sql->execute([$login_id, $password]);

                $rs = $sql->fetchAll(PDO::FETCH_ASSOC);

                if (count($rs) === 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception $e) {
                return false;
            }
        }

        //新規社員登録
        public function insert($login_id, $password){
            try {
                $sql = $this->pdo->prepare('insert into info(
                    login_id,
                    password,
                    name,
                    postno,
                    eino,
                    si_loss,
                    ei_loss) 
                    values(
                    ?,
                    ?,
                    ?,
                    "000-00",
                    "0000-000000-0",
                    0,
                    0
                )');

                $name = "新規社員($login_id)";
                $sql->execute([$login_id, $password, $name]);
                $row = $sql->rowCount();
                if ($row == 1) {
                    return true;
                } elseif ($row === 0) {
                    return false;
                }
            } catch (Exception $e) {
                return false;
            }
        }

        //delete
        public function delete($id){
            $constEnum = new ConstEnum();
            try {
                $sql = $this->pdo->prepare('update info set su=? where id=?');

                $sql->execute([$constEnum->getNON_USER(), $id]);
              
                return true;
            } catch (Exception $e) {
                return false;
            }
        }
























































































































        //~300岩瀬

        //302~502 堀木


        public function update($ary){
            try {
                $sql = $this->pdo->prepare('update info set name=?, furi=?, enterdate=?, postno=?, address=?, tel=?, birth=?, si=?, ei=?, eino=?, retire=?, si_loss=?, ei_loss=?, desknetspass=?, shareuser=?, sharepass=?, emailaddress=?, emailpass=?, su=?, password=? where id=?');

                $rs = $sql->execute([$ary['name'],$ary['furi'],$ary['enterdate'],$ary['postno'],$ary['address'],$ary['tel'],$ary['birth'],$ary['si'],$ary['ei'],$ary['eino'],$ary['retire'],$ary['si_loss'],$ary['ei_loss'],$ary['desknetspass'],$ary['shareuser'],$ary['sharepass'],$ary['emailaddress'],$ary['emailpass'],$ary['su'],$ary['password'],$ary['id']]);
                return $rs;

                if ($sql->rowCount() == 1) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception $e) {
                return false;
            }
        }





















































































































































































        //302~502 堀木

        //504~704 兼松

        //引数で指定されたログインID及びパスワードに一致するレコードがあるかを確認する。
        //テーブルからは社員ID及び権限を取得する。
        //一致するレコードがある場合は社員ID及び権限を返す。
        //一致しない場合はNULLを返す。
        //一致しない場合にNULLを返せている？
        //退職日より現在日時が過ぎていた場合はNULLを返す。

        public function infoCheck($login_id, $password){
            $constEnumDb = new ConstEnum();
            try {

                $sql = $this->pdo->prepare('select * from info where login_id = ? and password = ?');
                $sql->execute([$login_id, $password]);

                $rs = $sql->fetchAll(PDO::FETCH_ASSOC);

                $id = $rs[0]['id'];
                $su = $rs[0]['su'];

                if (count($rs) === 0) {
                    return null;
                }

                if ($su == $constEnumDb->getNON_USER()) {
                    return null;
                }

                $retire = $rs[0]['retire'];
                if ($retire !== null) {
                    $now = new \DateTime('now');

                    $retire = \DateTime::createFromFormat('Y-m-d', $retire);
                    $diff = $now->diff($retire);
                    if ($diff->invert === 1) {
                        return null;
                    }
                }
                //idとsuだけじゃなくて取得結果全部を返す
                return $rs;
            } catch (Exception $e) {
                return false;
            }
        }

        //資格保有情報の更新
        //引数で指定された内容でinfoテーブルの社員idが一致したレコードを更新する。
        //更新に成功した場合はtrueを返す。
        //失敗した場合はfalseを返す。

        public function updateLicense($ary, $columns){
            try {
                $sqlStr = 'update info set ';
                for ($i = 22; $i < 49; $i++) {
                    if ($i === 48) {
                        $sqlStr = $sqlStr . $columns[$i]['Field'] . "=:" . $columns[$i]['Field'];
                    } else {
                        $sqlStr = $sqlStr . $columns[$i]['Field'] . "=:" . $columns[$i]['Field'] . ",";
                    }
                }
                $sqlStr = $sqlStr . ' where id = :id';

                $sql = $this->pdo->prepare($sqlStr);

                $sql->execute($ary);

                return true;
                // if ($sql->rowCount() == 1) {
                //     return true;
                // } else {
                //     return false;
                // }
            } catch (Exception $e) {
                return false;
                // return $e;
               
            }
        }
    }
}























































































































//504~704 兼松