package me.exslodingdogs.anticheat.Checks.Movement;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Posiblity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class SPEED_check extends Check implements Listener {

    public SPEED_check(){
        super(CheckType.MOVENMENT, "SPEED", true);
    }

    HashMap<Player, Integer> Level = new HashMap<>();

    @EventHandler
    public void onmove(PlayerMoveEvent event){
        Player p = (Player) event.getPlayer();
        double speed = event.getFrom().toVector().distance(event.getTo().toVector());
        if(isEnabled() == false){
            return;
        }
        if(p.isDead()){
            return;
        }
        // p.sendMessage(cc("&aSpeed: &7" + speed));
        //p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSetSpeed: &7" + p.getWalkSpeed()*10));
        if(event.getFrom().getY() > event.getTo().getY()) {
            return;
        }

        if(p.getAllowFlight() == true) {
            return;
        }

        if(!p.getLocation().subtract(0,1,0).getBlock().getType().isSolid()) {
            return;
        }

        if(p.getWalkSpeed()*10 > 2.0) {
            if(speed > 0.9*p.getWalkSpeed()*10) {
                p.teleport(event.getFrom());
                event.setCancelled(true);
                if(Level.containsKey(p)) {
                    Level.put(p, Level.get(p)+1);
                    Flag(p, Posiblity.Certain, Level.get(p));
                }else {
                    Level.put(p, 1);
                    Flag(p, Posiblity.Certain, Level.get(p));
                }
            }
        }

        if(speed > 0.59 && speed < 0.6) {
            p.teleport(event.getFrom());
            if(Level.containsKey(p)) {
                Level.put(p, Level.get(p)+1);
                Flag(p, Posiblity.Certain, Level.get(p));
            }else {
                Level.put(p, 1);
                Flag(p, Posiblity.Certain, Level.get(p));
            }
        }

        if(speed > 0.9) {
            p.teleport(event.getFrom());
            event.setCancelled(true);
            if(Level.containsKey(p)) {
                Level.put(p, Level.get(p)+1);
                Flag(p, Posiblity.Certain, Level.get(p));
            }else {
                Level.put(p, 1);
                Flag(p, Posiblity.Certain, Level.get(p));
            }
        }
    }

}
