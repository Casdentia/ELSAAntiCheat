package me.exslodingdogs.anticheat.Checks.Movement.FLY;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Possibility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TypeA extends Check implements Listener {

    public TypeA(){
        super(CheckType.MOVEMENT, "FLY(TypeA)", true);
    }

    @EventHandler
    public void onmove(PlayerMoveEvent event){
        if(isEnabled() == false){
            return;
        }
        double speed = event.getFrom().toVector().distance(event.getTo().toVector());
        if(event.getPlayer().getAllowFlight() == true){
            return;
        }
        if(event.getFrom().getY() != event.getTo().getY()){
            //event.getPlayer().sendMessage("Chnaged!");
            return;
        }
        event.getPlayer().sendMessage("speed : " + speed);
        if(!event.getPlayer().getLocation().subtract(0,1,0).getBlock().getType().isSolid() && speed > 0.8){
            event.setCancelled(true);
            event.getPlayer().teleport(event.getFrom());
            flag(event.getPlayer(), Possibility.CERTAIN, 1);
        }

    }


}
