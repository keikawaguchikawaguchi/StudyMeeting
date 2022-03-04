
<?php
session_start();
require 'Const.php';
require 'DBInfo.php';
require 'function.php';

date_default_timezone_set('Asia/Tokyo');

//管理者か確認（意図しない画面推移を防ぐため）
$masterFlag = checkSu($_SESSION['su']);
error($masterFlag, 'screenErrMes', '../index.php');

//DBに接続するためにDBInfoをインスタンス生成
$dbInfo = new WorkManage\DBInfo();

//DBからすべての社員情報をとってくる
//結果を正しく取得できていないときfalse
$allList = $dbInfo->selectAll();


error($allList, 'dbErrMes', '../index.php');

//DBのカラムをすべて取得
//カラムのコメントも取得
//結果を正しく取得できていないときfalse
$columns = $dbInfo->getColumn();

error($columns, 'dbErrMes', '../index.php');

$constEnum = new WorkManage\ConstEnum();

$csvList = prepareAllList($allList, $columns, $constEnum->getCSV());

$now = date('Y_m_d_H_i_s', time());
$fileName = "../CSV/CSV_$now.csv";

touch($fileName);

$res = fopen($fileName, 'w');

//ファイルに書き込めない場合はエラーでlistにとぶ
error($res, 'notWrite', '../list.php');

foreach ($csvList as $value) {
	mb_convert_variables('SJIS-win', 'UTF-8', $value);
	fputcsv($res, $value);
}
fclose($res);


// ファイルのパス
$filePath = $fileName;

// リネーム後のファイル名
$fileRename = "社員情報一覧$now.csv";

// ファイルタイプを指定

header('Content-Type: application/octet-stream');

// ファイルサイズを取得し、ダウンロードの進捗を表示
header('Content-Length: ' . filesize($filePath));

// ファイルのダウンロード、リネームを指示
header('Content-Disposition: attachment; filename="' . $fileRename . '"');

// ファイルを読み込みダウンロードを実行
readfile($filePath);

exit();
