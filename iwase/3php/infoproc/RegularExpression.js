function newRegularExpression(){
    //idとnameで紐づけたHTMLタグの値をとってくる
    loginId = document.getElementsByName("login_id")[0].value;
    password = document.getElementsByName("password")[0].value;
    erroChara = document.getElementById("chara");
    erroCharaLength = document.getElementById("charaLength");
    newErrMes = document.getElementById("newErrMes");
    newErrMes.innerHTML = "";

    //エラーメッセージを隠す
    erroCharaLength.style.display = "none";
    erroChara.style.display = "none";

    pattern = /^[0-9a-zA-Z]+$/;

    //正規表現で正しければ true
    flagLoginId = pattern.test(loginId);
    flagPassword = pattern.test(password);

    //文字数が制限以下なら true
    flagLogCharaLength = loginId.length <= 100;
    flagPassCharaLength = password.length <= 100;

    flagChara = false;
    flagLength = false;

    //ログインかパスワードどちらか正規表現で間違って入ればエラーメッセージを表示させ
    //flagCharaをtrue
    //正しければtrueがはいるからfalseと一致してるということは間違っている
    if (flagLoginId === false || flagPassword === false) {
        //エラーメッセージを表示
        erroChara.style.display = "block";
        flagChara = true;
    }

    //正しければtrueがはいるからfalseと一致してるということは間違っている
    if (flagLogCharaLength === false || flagPassCharaLength === false) {
        erroCharaLength.style.display = "block";
        flagLength = true;
    }

    //正規表現か文字数どちらか正しくなければエラー
    if (flagChara === true || flagLength === true) {
        return false;
    } else {
        return true;
    }
}

//licenseのcheckboxとdateの値を全部持ってきて両方入力されているか両方されていないときtrue
function licenseRECheck(){
    //querySelectorAllでそれぞれのフォームの値を取得
    checkboxValues = document.querySelectorAll('input[type="checkbox"]');
    dateValues = document.querySelectorAll('input[type="date"]');
    textLength = document.getElementsByName('li_other')[0].value.length;
    licenseJsErro = document.getElementById('jsErro');
    licenseJsErroLength = document.getElementById('jsErroLength');

    //セッションに入ったエラーメッセージを隠す
    newErrMes = document.getElementById("newErrMes");
    newErrMes.innerHTML = "";

    //jsエラーメッセージを隠す
    licenseJsErro.style.display = "none";
    licenseJsErroLength.style.display = "none";

    checkDateFlag = true;

    for (let index = 0; index < dateValues.length; index++) {

        const checkboxValueFlag = checkboxValues[index].checked;
        const dateVlalue = dateValues[index].value;

        if (dateVlalue === '') {
            dataFlag = false;
        } else {
            dateFlag = true;
        }

        if (!checkboxValueFlag && dateVlalue || checkboxValueFlag && !dateVlalue) {

            licenseJsErro.style.display = "block";
            checkDateFlag = false;
            break;

        }
    }
    textLengthFlag = textLength <= 500;

    if (!textLengthFlag) {
        licenseJsErroLength.style.display = "block";
    }

    if (checkDateFlag && textLengthFlag) {
        return true;
    } else {
        return false;
    }
}

//空文字判定
//空文字ならfalse
function blankCheck(text){
    if (text.trim()) {
        return true;
    } else {
        return false;
    }
}

function editErroMess(bool){
    if (bool) {
        return true;
    } else {
        document.getElementsByName('name')[0].focus();
        document.getElementsByName('name')[0].blur();
        editJsErroMes = document.getElementById('editErroText');
        editJsErroMes.style.display = "block";
        return false;
    }
}

//edit.phpの一般社員正規表現
//画面レイアウト修正画面、空白だけはNG
function editUserRegularExpression(){


nameText = document.getElementsByName('name')[0].value; //0
furiText = document.getElementsByName('furi')[0].value; //1
postnoText = document.getElementsByName('postno')[0].value; //2
addressText = document.getElementsByName('address')[0].value; //3
telText = document.getElementsByName('tel')[0].value; //4


editText = [nameText,furiText,postnoText,addressText,telText,];

let posPattern = /^[0-9]{3}-[0-9]{4}$/;
let kigouPattern = /^[0-9a-zA-Z -~]+$/;
let pattern = /^[0-9a-zA-Z]+$/;

let telPattern = {
    test : "a",
    normal : /^0[^346]\d{8}$/,
    mobile : /^0[0-9]{10}$/,
    tokyo : /^0[36][0-9]{8}$/,
    none   : /^[0-9]{7}$/
};

//セッションに入ったエラーメッセージを隠す
newErrMes = document.getElementById("newErrMes");
newErrMes.innerHTML = "";

//jsのエラーメッセージをけす
editJsErroMes = document.getElementById('editErroText');
editJsErroMes.style.display = "none";



//空白チェック
for (var i = 0; i < editText.length; i++) {
   if(!blankCheck(editText[i])){

    return false;
} 
}



//文字数チェック&各種正規表現

if (editText[0].length > 100) {

  return false;
}

if (editText[1].length > 100) {

  return false;
}

if (!posPattern.test(editText[2])) {

    return false;
}

if (editText[3].length > 300) {

    return false;

} 

if (
    !telPattern['normal'].test(editText[4]) &&
    !telPattern['mobile'].test(editText[4]) &&
    !telPattern['tokyo'].test(editText[4]) &&
    !telPattern['none'].test(editText[4]) ){


    return false;
}

return true;
}


//edit.phpの管理者用正規表現
//画面レイアウト修正画面、空白だけはNG
function editAdminRegularExpression(){

nameText = document.getElementsByName('name')[0].value; //0
furiText = document.getElementsByName('furi')[0].value; //1
postnoText = document.getElementsByName('postno')[0].value; //2
addressText = document.getElementsByName('address')[0].value; //3
telText = document.getElementsByName('tel')[0].value; //4
einoText = document.getElementsByName('eino')[0].value; //5
desknetspassText = document.getElementsByName('desknetspass')[0].value; //6
shareuserText = document.getElementsByName('shareuser')[0].value; //7
sharepassText = document.getElementsByName('sharepass')[0].value; //8
emailaddressText = document.getElementsByName('emailaddress')[0].value; //9
emailpassText = document.getElementsByName('emailpass')[0].value; //10
passwordText = document.getElementsByName('password')[0].value; //11

editText = [nameText,furiText,postnoText,addressText,telText,
einoText,desknetspassText,shareuserText,sharepassText,emailaddressText,emailpassText,passwordText];

let posPattern = /^[0-9]{3}-[0-9]{4}$/;
let einoPattern = /^[0-9]{4}-[0-9]{6}-[0-9]{1}$/;
let kigouPattern = /^[0-9a-zA-Z -~]+$/;
let pattern = /^[0-9a-zA-Z]+$/;

let telPattern = {
    test : "a",
    normal : /^0[^346]\d{8}$/,
    mobile : /^0[0-9]{10}$/,
    tokyo : /^0[36][0-9]{8}$/,
    none   : /^[0-9]{7}$/
};

//セッションに入ったエラーメッセージを隠す
newErrMes = document.getElementById("newErrMes");
newErrMes.innerHTML = "";

//jsのエラーメッセージをけす
editJsErroMes = document.getElementById('editErroText')
editJsErroMes.style.display = "none";


//空白チェック
for (var i = 0; i < editText.length; i++) {
   if(!blankCheck(editText[i])){

    return false;
} 
}



//文字数チェック
for (var i = 0; i < editText.length; i++) {
    if (i === 2) {
        if (!posPattern.test(editText[i])) {

            return false;
        }
    } else if (i === 3) {
       if (editText[i].length > 300) {

        return false;
    }
} else if (i === 4) {

   if (
    !telPattern['normal'].test(editText[4]) &&
    !telPattern['mobile'].test(editText[4]) &&
    !telPattern['tokyo'].test(editText[4]) &&
    !telPattern['none'].test(editText[4]) ){


    return false;
}
} else if (i === 5) {
   if (!einoPattern.test(editText[i])) {

      return false;
  }
} else if (i===6||i===7||i===8) {
 if (editText[i].length > 100 || !kigouPattern.test(editText[i])) {

    return false;
}
} else if (i === 9) {
   if (editText[i].length > 500 || editText[i].split('@')[1] !== 'intecs.ne.jp') {

      return false;
  }
} else if (i===10||i===11) {
 if (editText[i].length > 100 || !pattern.test(editText[i])) {

    return false;
}
} else if (editText[i].length > 100) {

  return false;
}
}


return true;
}
