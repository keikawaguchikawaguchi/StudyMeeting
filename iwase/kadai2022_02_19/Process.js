//カウントダウンを表示する処理とボタンを押したときに回数をカウントする処理
//一回目enterでタイマー起動二回目以降は回数蓄積タイマー終了後は何も処理されない。
class Process {


    //一回目のクリック時はtrue
    firstClickFlag = true;

    //カウントダウンの時間を取得
    countdownTime = document.getElementById('timeBox');

    //タイマーを表示させる場所を取得
    countdownField = document.getElementById('countdownField');

    //打鍵数を表示させる場所を取得
    barrageNumField = document.getElementById('barrageNumField');

    //説明
    explanation = document.getElementById('explanation');

    //リトライボタン
    retry = document.getElementById('retry');

    //ミリ秒
    milli = 100;

    //連打回数
    barrageNum = 0;

    //buttonを押したときの処理一回目はカウントダウンスタート二回目は連打処理
    buttonClick() {

        //カウントダウンの時間が正しく入力されていなかったら処理をやめる
        if (!this.countdownTime.value || this.countdownTime.value <= 0) {

            this.countdownField.innerHTML = '制限時間を正しく入力してください';

            //処理から抜けるためにとりあえずreturnしてる
            return false;
        }

        //Flagがtrueなので一回目の処理が行われる
        if (this.firstClickFlag) {


            //タイマースタート
            this.countdownProcess(this.countdownTime.value);

            this.explanation.innerHTML = 'Enter連打!!'

            this.firstClickFlag = false;

        } else if (this.firstClickFlag === false) {

            //二回目以降の処理
            this.barrage();

        } else {
            //タイマー終了後は何も実行されない
            return;
        }

    }

    //タイマー処理
    countdownProcess(secound) {

        //10ミリ秒ごとにタイマーの表示を変更する
        const timer = setInterval(() => {

            this.countdownField.innerHTML = secound + '秒' + this.milli + 'ミリ秒';
            this.milli--;

            //0ミリ秒になったら1秒減る
            if (this.milli == 0) {

                this.countdownField.innerHTML = secound + '秒' + this.milli + 'ミリ秒';

                secound--;

                //ミリ秒は繰り返さないといけなので100に戻す
                this.milli = 100;

                //秒の値が0になったらカウントダウン終了
                if (secound == 0) {

                    //0秒00ミリ秒とひょうじしないと1秒00ミリ秒と表示される
                    //secound == 0でタイマーが終了なのでsecoundが1から0に減らない
                    this.countdownField.innerHTML = '0秒00ミリ秒';

                    //リトライボタン表示
                    this.retry.style.display = 'block';

                    //タイマー終了
                    clearInterval(timer);

                    //flagを空文字にしてタイマーが終了した後enterを押しても何も実行されないようにする
                    this.firstClickFlag = "";

                    this.explanation.innerHTML = '終了';
                }
            }

        }, 10);
    }

    //連打処理
    barrage() {

        //buttonを押すたびに回数を増やす
        this.barrageNum++;

        this.barrageNumField.innerHTML = this.barrageNum + '回';
    }

}