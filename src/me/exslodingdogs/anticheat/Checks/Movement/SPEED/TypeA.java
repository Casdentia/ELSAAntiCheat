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

public class TypeA extends Check implements Listener {

    public TypeA(){
        super(CheckType.MOVEMENT, "SPEED(TypeA)", true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();
        double speed = from.toVector().distance(to.toVector());

        if(!player.getLocation().subtract(0,1,0).getBlock().getType().isSolid()){
            return;
        }

        /*

    This has some bugs

     */

        player.sendMessage(ChatColor.RED + "SPEED: " + ChatColor.GRAY + speed);
        if((to.getY() - from.getY()) == 0){
            if(speed > 0.79){
                flag(player, Possibility.CERTAIN, 1);
                player.teleport(from);
            }

            if(speed > 0.49 && speed < 0.525){
                flag(player, Possibility.POSSIBLE, 1);
                player.teleport(from);
            }
            if(speed > 0.69 && speed < 0.7){
                flag(player, Possibility.CERTAIN, 1);
                player.teleport(from);
            }
            if(speed > 0.66 && speed < 0.67){
                flag(player, Possibility.CERTAIN, 1);
                player.teleport(from);
            }
            if(speed > 0.41 && speed < 0.429){
                flag(player, Possibility.POSSIBLE, 1);
                player.teleport(from);
            }
            if(speed > 0.54 && speed < 0.579){
                flag(player, Possibility.CERTAIN, 1);
                player.teleport(from);
            }

            if(speed > 0.28061664 && speed < 0.28061669 && !player.isSprinting()){
                flag(player, Possibility.POSSIBLE, 1);
                player.teleport(from);
            }
            if(speed > 0.146 && speed < 0.149){
                flag(player, Possibility.POSSIBLE, 1);
                player.teleport(from);
            }
            if(speed > 0.1738 && speed < 0.1762){
                flag(player, Possibility.POSSIBLE, 1);
                player.teleport(from);
            }
            if(speed > 0.2899 && speed < 0.29){
                flag(player, Possibility.CERTAIN, 1);
                player.teleport(from);
            }
            if(speed > 0.62739 && speed < 0.6274){
                flag(player, Possibility.CERTAIN, 1);
                player.teleport(from);
            }
            return;
        }

        if((to.getY() - from.getY()) > 0){
            if((to.getY() - from.getY()) > 0.2 && (to.getY() - from.getY()) < 0.35){
                return;
            }
            if((to.getY() - from.getY()) > 0.9){
                return;
            }
            if(speed > 0.8){
                event.setCancelled(true);
                player.teleport(from);
                flag(player, Possibility.CERTAIN, 1);
                return;
            }

            return;
        }

    }

}
