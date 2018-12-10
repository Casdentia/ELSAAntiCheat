package me.exslodingdogs.anticheat.Checks.Movement.NOFALL;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.exslodingdogs.anticheat.Checks.CheckResult;

public class TypeA {
	
public static CheckResult runcheck(Player player, Location from, Location to, double speed) {
		
		if(player.getGameMode() == GameMode.CREATIVE) {
			return CheckResult.PASS;
		}
		
		if(from.getY() <= to.getY()) {
			return CheckResult.PASS;
		}
		
		if(player.getVehicle() != null) {
			return CheckResult.PASS;
		}
		
		if(player.getFallDistance() == 0.0 && speed > 0.59) {
			return CheckResult.FAIL;
		}
		
		return CheckResult.PASS;
	}
	
}
