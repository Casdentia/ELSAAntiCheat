package me.exslodingdogs.anticheat.Checks.Movement.FLY;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.exslodingdogs.anticheat.Checks.CheckResult;

public class TypeC implements Listener {

    
    public static CheckResult runCheck(Player player, Location from, Location to, double speed, double jumphight) {
    	if(player.getAllowFlight() == true){
    		return CheckResult.PASS;
        }
        if(from.getBlock().getType() == Material.WATER){
            return CheckResult.PASS;
        }
        if(player.getLocation().subtract(0,1,0).getBlock().getType() == Material.WATER){
        	return CheckResult.PASS;
        }
        if(player.getLocation().subtract(0,1,0).getBlock().isLiquid()){
        	return CheckResult.PASS;
        }
        if(from.getY() > to.getY()){
        	return CheckResult.PASS;
        }
        if(from.getY() < to.getY()){
            if(speed > 0.21 && speed < 0.28){
                if(jumphight > 0.5 && jumphight < 0.55){
                    return CheckResult.PASS;
                }
                if(jumphight > 0.0196 && jumphight < 0.0197){
                    return CheckResult.FAIL;
                }
                return CheckResult.PASS;
            }
            return CheckResult.PASS;
        }
		return CheckResult.PASS;
    }

}
