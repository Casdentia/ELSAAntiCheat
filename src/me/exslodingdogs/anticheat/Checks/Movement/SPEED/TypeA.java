package me.exslodingdogs.anticheat.Checks.Movement.SPEED;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.exslodingdogs.anticheat.Checks.CheckResult;

public class TypeA implements Listener {


    public static CheckResult runCheck(Player player, double speed, double jumphight) {
    	if(jumphight > 0){
            if(jumphight > 0.3 && jumphight < 0.5){
                if(speed > 0.79){
                    return CheckResult.FAIL;                }
            }
            return CheckResult.PASS;
    	}
           return CheckResult.PASS;
    }
    
}
