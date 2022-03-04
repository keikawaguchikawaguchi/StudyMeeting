<?php
namespace WorkManage {
    
    class ConstEnum {

       private const INSURANCE = ['NOT_SUBSCRIBED' => 0, 'SUBSCRIBED' => 1];
       private const LOSSINSURANCE = ['NOT_LOSSED' => 0, 'LOSSED' => 1];
       private const ADMINSTATE = ['USER' => 0, 'ADMIN' => 1,'NON_USER' =>2];
       private const SKILL = ['UN_ACQUISIION' => 0, 'ACQUISITION' => 1];

       private const ALLLIST = ['LIST' => 0,'CSV'=> 1 ];


       public function getNOT_SUBSCRIBED(){
        return self::INSURANCE['NOT_SUBSCRIBED'];
    }

    public function getSUBSCRIBED(){
        return self::INSURANCE['SUBSCRIBED'];
    }

    public function getNOT_LOSSED(){
        return self::LOSSINSURANCE['NOT_LOSSED'];
    }

    public function getLOSSED(){
        return self::LOSSINSURANCE['LOSSED'];
    }


    public function getUSER(){
        return self::ADMINSTATE['USER'];
    }

    public function getADMIN(){
        return self::ADMINSTATE['ADMIN'];
    }

    public function getNON_USER(){
        return self::ADMINSTATE['NON_USER'];
    }

    public function getUN_ACQUISIION(){
        return self::SKILL['UN_ACQUISIION'];
    }

    public function getACQUISITION(){
        return self::SKILL['ACQUISITION'];
    }

    public function getLIST(){
        return self::ALLLIST['LIST'];
    }

    public function getCSV(){
        return self::ALLLIST['CSV'];
    }

}
}