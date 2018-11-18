package me.exslodingdogs.anticheat.Checks.Movement;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckResult;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Movement.FLY.FLYSPEED;
import me.exslodingdogs.anticheat.Checks.Possibility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Flight_check extends Check implements Listener {

    public Flight_check(){
        super(CheckType.MOVENMENT, "FLY");
    }

    @EventHandler
    public void oncheck(PlayerMoveEvent event){
        Player p = event.getPlayer();
        double speed = event.getFrom().toVector().distance(event.getTo().toVector());
        if(p.getAllowFlight() == true){
            return;
        }

        if(event.getFrom().getY() > event.getTo().getY()){
            return;
        }
        if(event.getFrom().getY() < event.getTo().getY()){
            return;
        }
        if(p.getLocation().subtract(0,1,0).getBlock().getType().isSolid() == true){
            return;
        }
        FLYSPEED.FLYSPEEDCheck(speed);
        if(FLYSPEED.FLYSPEEDCheck(speed) == CheckResult.Fail){
            p.teleport(event.getFrom());
            flag(p, Possibility.Certain, 1);
        }

    }

}
