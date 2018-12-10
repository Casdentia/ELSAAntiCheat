package me.exslodingdogs.anticheat.Checks.Movement.SPEED;

import me.exslodingdogs.anticheat.Checks.CheckResult;

public class TypeF {

	public static CheckResult runCheck(double speed, double jumphight) {
		if(jumphight > 0.16477 && jumphight < 0.165) {
			if(speed > 0.317 && speed < 0.32) {
				return CheckResult.FAIL;
			}
			return CheckResult.PASS;
		}
		return CheckResult.PASS;
	}
	
}
