package me.exslodingdogs.anticheat.Checks.Movement.FLY;

import org.bukkit.entity.Player;

import me.exslodingdogs.anticheat.Checks.CheckResult;

public class TypeA{

    
    public static CheckResult runCheck(Player player, double speed, double jumphight) {
    	if(player.getAllowFlight() == true){
            return CheckResult.PASS;
        }
        if(jumphight != 0){
            return CheckResult.PASS;
        }
        if(!player.getLocation().subtract(0,1,0).getBlock().getType().isSolid() && speed > 0.8){
        	return CheckResult.FAIL;
        }
        return CheckResult.PASS;
    }
    

}
