package me.exslodingdogs.anticheat.Checks.Movement.SPEED;

import org.bukkit.event.Listener;

import me.exslodingdogs.anticheat.Checks.CheckResult;

public class TypeB implements Listener {

	public static CheckResult runcheck(double speed, double jumphight){
    	if(jumphight > 0){
            if(jumphight > 0.2999999999999998 && jumphight < 0.5){
                if((speed > 0.351 && speed < 0.66) || (speed > 0.24 && speed < 0.3)){
                    if(jumphight > 0.41 && jumphight < 0.42){return CheckResult.PASS;}
                    if(jumphight > 0.404 && jumphight < 0.405){return CheckResult.PASS;}
                    if(jumphight > 0.33 && jumphight < 0.34){return CheckResult.PASS;}
                    return CheckResult.FAIL;
                }               
            }
            return CheckResult.PASS;
        }
		return CheckResult.PASS;
    }
	
}
