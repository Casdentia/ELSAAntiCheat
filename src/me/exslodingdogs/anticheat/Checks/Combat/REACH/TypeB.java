package me.exslodingdogs.anticheat.Checks.Combat.REACH;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.exslodingdogs.anticheat.Checks.CheckResult;

public class TypeB {
	public static CheckResult runCheck(Player player, Entity entity) {
    	if(player.getGameMode() == GameMode.CREATIVE) {
    		return CheckResult.PASS;
    	}
    	double distance = player.getEyeLocation().toVector().distanceSquared(entity.getLocation().toVector());
    	if(distance > 14.78) {
    		//player.sendMessage(distance + " == " + player.getLocation().toVector().distance(entity.getLocation().toVector()));
    		return CheckResult.FAIL;
    	}
    	return CheckResult.PASS;
    }
}
