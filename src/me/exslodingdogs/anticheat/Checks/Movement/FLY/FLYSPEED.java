package me.exslodingdogs.anticheat.Checks.Movement.FLY;

import me.exslodingdogs.anticheat.Checks.CheckResault;

public class FLYSPEED {

    public static CheckResault FLYSPEEDCheck(double speed){
        if(speed > 8){return CheckResault.Fail;}
        if(speed < 0.2179 && speed > 0.2179){return CheckResault.Fail;}


        return CheckResault.PASS;
    }

}
