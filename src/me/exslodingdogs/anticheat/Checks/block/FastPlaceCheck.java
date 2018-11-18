package me.exslodingdogs.anticheat.Checks.block;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class FastPlaceCheck extends Check implements Listener {

    public FastPlaceCheck(CheckType type, String nameofhack) {
        super(type, nameofhack);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {


    }

}
