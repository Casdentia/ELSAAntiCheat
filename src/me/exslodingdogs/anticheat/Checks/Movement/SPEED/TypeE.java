package me.exslodingdogs.anticheat.Checks.Movement.SPEED;

import me.exslodingdogs.anticheat.Checks.CheckResult;

public class TypeE {

	public static CheckResult runCheck(double speed, double jumphight) {
		if(jumphight > 0.33 && jumphight < 0.419999999) {
			if(speed > 0.51 && speed < 0.597) {
				return CheckResult.FAIL;
			}
			if(speed > 0.614 && speed < 0.615) {
				return CheckResult.FAIL;
			}
			return CheckResult.PASS;
		}
		return CheckResult.PASS;
	}
	
}
