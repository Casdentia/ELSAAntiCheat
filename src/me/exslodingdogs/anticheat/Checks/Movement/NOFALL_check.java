package me.exslodingdogs.anticheat.Checks.Movement;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Possibility;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class NOFALL_check extends Check implements Listener {

    public NOFALL_check(){
        super(CheckType.MOVENMENT, "NOFALL");
    }

    ArrayList<Player> needschecking = new ArrayList<>();
    HashMap<Player, Integer> Level = new HashMap<>();
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player p = event.getPlayer();
        double i = event.getTo().toVector().distance(event.getFrom().toVector());
        boolean onground = false;
        if(event.getFrom().getY() < event.getTo().getY()){
            return;
        }

        if(p.getAllowFlight() == true || p.getGameMode() == GameMode.CREATIVE){
            return;
        }

        if(!p.getLocation().subtract(0,1,0).getBlock().getType().isSolid() && p.getLocation().subtract(0,1,0).getBlock() != null){
            onground = false;
        }else{
            onground = true;
        }
        if(p.isFlying() == true){
            return;
        }
        if(event.getFrom().getY() > event.getTo().getY()){
            if(p.getFallDistance() == 0.0){
                if(onground == false){
                    if(i > 0.59){
                        if(Level.containsKey(p)){
                            Level.put(p, Level.get(p)+1);
                        }else{
                            Level.put(p, 1);
                        }
                        flag(p, Possibility.Certain, Level.get(p));
                        p.teleport(event.getFrom());
                    }
                }
            }
        }

        //p.sendMessage(p.getFallDistance() + "");
        //p.sendMessage(i + "");
        //p.sendMessage(onground + "");

    }

}
