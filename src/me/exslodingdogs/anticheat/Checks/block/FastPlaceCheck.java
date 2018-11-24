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

    private Map<UUID, Pair<Long, Short>> traces = new HashMap<>();

    public FastPlaceCheck() {
        super(CheckType.BLOCK, "fast-place");
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        long placeTime = System.currentTimeMillis();
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();


        if (!traces.containsKey(uuid)) {
            traces.put(uuid, Pair.of(placeTime, (short) 0));
            return;
        }

        Pair<Long, Short> pair = traces.get(uuid);

        long between = placeTime - pair.getLeft();

        boolean alert = false;
        if (between < 150) {
            alert = true;
            super.flag(player, Possibility.POSSIBLE, pair.getValue());
        }

        System.out.println("Time between: " + between + "ms.");

        traces.put(player.getUniqueId(), Pair.of(placeTime, (short) (alert ? pair.getRight() + 1 : pair.getRight())));
    }

}
