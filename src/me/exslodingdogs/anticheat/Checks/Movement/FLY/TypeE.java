package me.exslodingdogs.anticheat.Checks.Movement.FLY;

import org.bukkit.entity.Player;

import me.exslodingdogs.anticheat.Checks.CheckResult;

public class TypeE {

	public static CheckResult runCheck(Player player, double speed, double jumphight) {
		if(player.isFlying()) {
			return CheckResult.PASS;
		}
		
		if(jumphight != 0) {
			return CheckResult.PASS;
		}
		if(player.getLocation().subtract(0,1,0).getBlock().getType().isSolid() ||
				player.getLocation().subtract(0,1,0).getBlock().isLiquid()) {
			return CheckResult.PASS;
		}
		if(speed > 0.0979983 && speed < 0.098) {
			return CheckResult.FAIL;
		}
		return CheckResult.PASS;
	}
	
}
