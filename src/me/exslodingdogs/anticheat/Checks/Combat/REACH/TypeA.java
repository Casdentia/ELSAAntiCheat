package me.exslodingdogs.anticheat.Checks.Combat.REACH;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import me.exslodingdogs.anticheat.Checks.CheckResult;

public class TypeA{


    
    public static CheckResult runCheck(Player player, double distance) {
    	if(player.getGameMode() == GameMode.CREATIVE) {
    		return CheckResult.PASS;
    	}
    	if(distance > 4){
    		return CheckResult.FAIL;
    	}
    	return CheckResult.PASS;
    }

}
