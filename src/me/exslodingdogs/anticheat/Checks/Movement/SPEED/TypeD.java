package me.exslodingdogs.anticheat.Checks.Movement.SPEED;

import me.exslodingdogs.anticheat.Checks.CheckResult;

public class TypeD {

	public static CheckResult runCheck(double speed, double jumphight) {
		//Bukkit.getConsoleSender().sendMessage("before check :: speed : " + speed + " junphight : " + jumphight);
		if(jumphight > 0 && jumphight < 0.08) {
			if((speed > 0.19 && speed > 0.20) || (speed > 0.209)) {
				//Bukkit.getConsoleSender().sendMessage("Flagged :: speed : " + speed + " junphight : " + jumphight);
				return CheckResult.FAIL;
			}else {
				//Bukkit.getConsoleSender().sendMessage("noflag :: speed : " + speed + " junphight : " + jumphight);
				return CheckResult.PASS;
			}
			
		}
		return CheckResult.PASS;
	}
	
}
