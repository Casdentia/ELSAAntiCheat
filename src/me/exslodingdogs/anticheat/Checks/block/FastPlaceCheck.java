package me.exslodingdogs.anticheat.Checks.block;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Possibility;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FastPlaceCheck extends Check implements Listener {

    private Map<UUID, Pair<Long, Short>> traceMap = new HashMap<>();

    public FastPlaceCheck() {
        super(CheckType.BLOCK, "FAST-PLACE", false);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        long placeTime = System.currentTimeMillis();
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();


        if (!traceMap.containsKey(uuid)) {
            traceMap.put(uuid, Pair.of(placeTime, (short) 0));
            return;
        }

        Pair<Long, Short> pair = traceMap.get(uuid);

        long between = placeTime - pair.getLeft();

        boolean alert = false;

        /* if time between block place is < 150; flag the player */
        if (between < 150) {
            alert = true;
            super.Flag(player, Possibility.POSSIBLE, pair.getValue());
        }

        traceMap.put(player.getUniqueId(), Pair.of(placeTime, (short) (alert ? pair.getRight() + 1 : pair.getRight())));
    }

}
