package me.exslodingdogs.anticheat.Checks.Movement.FLY;

import me.exslodingdogs.anticheat.Checks.CheckResult;

public class FLYSPEED {

    public static CheckResult FLYSPEEDCheck(double speed){
        if(speed > 8){return CheckResult.FAIL;}
        if(speed < 0.2179 && speed > 0.2179){return CheckResult.FAIL;}


        return CheckResult.PASS;
    }

}
