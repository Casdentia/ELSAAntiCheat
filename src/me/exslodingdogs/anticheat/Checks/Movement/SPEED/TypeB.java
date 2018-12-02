package me.exslodingdogs.anticheat.Checks.Movement.SPEED;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Possibility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TypeB extends Check implements Listener {

    public TypeB(){
        super(CheckType.MOVEMENT, "SPEED(TypeB)", true);
    }
    /*

    This is not finished yet

     */

    @EventHandler
    public void onmove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location to = event.getTo();
        Location from = event.getFrom();
        double speed = from.toVector().distance(to.toVector());
        double jumphight = to.getY() - from.getY();

        //player.sendMessage(ChatColor.RED + "SPEED: " + ChatColor.GRAY + speed + ChatColor.RED + "  JUMPHIGHT: " + ChatColor.GRAY + jumphight);
        if(jumphight > 0){

            if(jumphight > 0.3 && jumphight < 0.5){

                if(speed > 0.24 && speed < 0.7){
                    if(jumphight > 0.41 && jumphight < 0.42){return;}
                    if(jumphight > 0.404 && jumphight < 0.405){return;}
                    if(jumphight > 0.33 && jumphight < 0.34){return;}
                    event.setCancelled(true);
                    player.teleport(from);
                    flag(player, Possibility.POSSIBLE, 1);
                    return;
                }
                if(speed > 0.8){
                    event.setCancelled(true);
                    player.teleport(from);
                    flag(player, Possibility.POSSIBLE, 1);
                    return;
                }
            }
            return;
        }

    }

}
