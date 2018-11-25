package me.exslodingdogs.anticheat.Checks.Movement.FLY;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Possibility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TypeC extends Check implements Listener {

    public TypeC(){
        super(CheckType.MOVEMENT, "FLY(TypeC)", true);
    }
/*

this is for hard to detect checks
 */
    @EventHandler
    public void onmove(PlayerMoveEvent event){
        if(isEnabled() == false){
            return;
        }
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();
        double speed = event.getFrom().toVector().distance(event.getTo().toVector());
        if(event.getPlayer().getAllowFlight() == true){
            return;
        }
        if(from.getBlock().getType() == Material.WATER){
            return;
        }
        if(player.getLocation().subtract(0,1,0).getBlock().getType() == Material.WATER){
            return;
        }
        if(player.getLocation().subtract(0,1,0).getBlock().isLiquid()){
            return;
        }
        if(event.getFrom().getY() > event.getTo().getY()){
            return;
        }
        if(event.getFrom().getY() == event.getTo().getY()){
                if(event.getPlayer().getLocation().subtract(0,1,0).getBlock().getType().isSolid()){
                    return;
                }
                if(speed > 0.19 && speed < 0.24){
                    flag(player, Possibility.POSSIBLE, 1);
                    player.teleport(from);
                }
            return;
        }
        if(from.getY() < to.getY()){

                if(speed > 0.21 && speed < 0.28){
                    if((to.getY() - from.getY()) > 0.0196 && (to.getY() - from.getY()) < 0.0197){
                        flag(player, Possibility.POSSIBLE, 1);
                        player.teleport(from);
                    }

                }
                if(speed > 0.30000004 && speed < 0.30000009){
                    if(speed < 0.30000003 && speed > 0.30000001){
                        return;
                    }
                    flag(player, Possibility.POSSIBLE, 1);
                    player.teleport(from);
                }
            return;
        }

    }

}
