package me.exslodingdogs.anticheat.Checks.Movement.FLY;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Possibility;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TypeB extends Check implements Listener {

    public TypeB(){
        super(CheckType.MOVEMENT, "FLY(TypeB)", true);
    }

    @EventHandler
    public void onmove(PlayerMoveEvent event){
        if(isEnabled() == false){
            return;
        }
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();
        double speed = from.toVector().distance(to.toVector());

        if(from.getY() >= to.getY()){
            return;
        }
        if((to.getY() - from.getY()) >= 0.5){
            if(speed >= 0.79){
                flag(player, Possibility.POSSIBLE, 1);
                player.teleport(from);
            }
        }
    }

}
