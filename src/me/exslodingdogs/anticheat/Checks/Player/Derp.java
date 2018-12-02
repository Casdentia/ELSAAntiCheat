package me.exslodingdogs.anticheat.Checks.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Derp implements Listener {

    @EventHandler
    public void onmove(PlayerMoveEvent event){
        event.getPlayer().sendMessage("pitch: " + event.getPlayer().getLocation().getPitch());
        if(event.getPlayer().getLocation().getPitch() > 90){
            event.getPlayer().sendMessage("Derp Flagged!");
        }
    }

}
