package me.exslodingdogs.anticheat.Checks.Movement.FLY;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.exslodingdogs.anticheat.Checks.CheckResult;

public class TypeB implements Listener {

    
    public static CheckResult runCheck(Player player, double speed, double jumphight) {
         if(jumphight >= 0.5){
             if(speed >= 0.79){
                return CheckResult.FAIL;
             }else {
            	 return CheckResult.PASS;
             }
         }
         return CheckResult.PASS;
    }

}
