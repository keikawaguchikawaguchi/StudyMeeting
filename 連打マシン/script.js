
var timer1;
var countup=0;
var frag=0;

var cntstart=document.getElementById('srt');
var renda=document.getElementById('ren');
var count=document.getElementById('count');

//連打ボタンをクリックしたときfragが1なら回数を表示
renda.onclick=function()
{
    if(frag==1){
    countup+=1;
    count.innerHTML=countup;
    }
}

//スタートボタンをクリックしたときfragを1にして10秒カウントダウンを行う
cntstart.onclick =function()
{
  frag=1;
  //複数回クリック防止
  cntstart.disabled=true;
  timer1=setInterval("countDown()",1000);
}

function countDown()
{
  var time=document.timer.elements[0].value;
  
  if(time=="0") 
  {
    alert("10秒が経過しました。記録は"+countup+"回です");
    reSet();
  }
  else
  { 
    document.timer.elements[0].value=time-1;
  }
}

//リセット処理
function reSet()
{
  document.timer.elements[0].value="10";
  clearInterval(timer1);
  count.innerHTML="0";
  countup=0;
  frag=0;
  cntstart.disabled=false;
}  

