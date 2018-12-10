package me.exslodingdogs.anticheat.Checks.Movement.SPEED;

import me.exslodingdogs.anticheat.Checks.CheckResult;

public class TypeC {

	public static CheckResult runcheck(double speed, double jumphight) {
		if(jumphight != 0) {
			return CheckResult.PASS;
		}
		if(speed > 0.3399 && speed < 0.35) {
			return CheckResult.FAIL;
		}
		return CheckResult.PASS;
	}
	
}
