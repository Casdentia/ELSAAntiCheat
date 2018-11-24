package me.exslodingdogs.anticheat.Checks.Movement;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Possibility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

public class SPEED_check extends Check implements Listener {

    public SPEED_check(){
        super(CheckType.MOVEMENT, "SPEED");
    }

    private HashMap<UUID, Integer> levelMap = new HashMap<>();

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        double speed = event.getFrom().toVector().distance(event.getTo().toVector());

        player.sendMessage(cc("&aSpeed: &7" + speed));
        //p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSetSpeed: &7" + p.getWalkSpeed()*10));

        if(event.getFrom().getY() > event.getTo().getY()) {
            return;
        }

        if(player.getAllowFlight()) {
            return;
        }

        if(!player.getLocation().subtract(0,1,0).getBlock().getType().isSolid()) {
            return;
        }

        if(player.getWalkSpeed()*10 > 2.0) {
            if(speed > 0.9*player.getWalkSpeed()*10) {
                player.teleport(event.getFrom());
                event.setCancelled(true);
                handleLevel(player);
            }
        }

        if(speed > 0.59 && speed < 0.6) {
            player.teleport(event.getFrom());
            handleLevel(player);
        }

        if(speed > 0.9) {
            player.teleport(event.getFrom());
            event.setCancelled(true);
            handleLevel(player);
        }
    }

    private void handleLevel(Player player) {
        int level;
        UUID uuid = player.getUniqueId();
        levelMap.put(uuid, (level = (levelMap.get(uuid) == null ? 0 : levelMap.get(uuid)) + 1));
        flag(player, Possibility.CERTAIN, level);
    }

}
