package me.exslodingdogs.anticheat.Checks.block;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.entity.Player;

import me.exslodingdogs.anticheat.Checks.CheckResult;

public class FastPlaceCheck{

    private static Map<UUID, Pair<Long, Short>> traceMap = new HashMap<>();
    
    public static CheckResult runCheck(Player player) {    	
    	long placeTime = System.currentTimeMillis();
        UUID uuid = player.getUniqueId();
        if (!traceMap.containsKey(uuid)) {
            traceMap.put(uuid, Pair.of(placeTime, (short) 0));
            return CheckResult.PASS;
        }
        Pair<Long, Short> pair = traceMap.get(uuid);
        long between = placeTime - pair.getLeft();
        boolean alert = false;
        //player.sendMessage(between + "");
        if (between < 70 && between > 29) {
            alert = true;
            return CheckResult.FAIL;
        }
        if(between == 3 || between == 2) {
        	alert = true;
            return CheckResult.FAIL;
        }
        traceMap.put(player.getUniqueId(), Pair.of(placeTime, (short) (alert ? pair.getRight() + 1 : pair.getRight())));    	
    	return CheckResult.PASS;
    }
    
}
