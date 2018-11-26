package me.exslodingdogs.anticheat.Checks.Movement;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Posiblity;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class NOSLOW_check extends Check implements Listener {

    public NOSLOW_check(){
        super(CheckType.MOVENMENT, "NOSLOW", true);
    }
    HashMap<Player, Integer> Flags = new HashMap<>();

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player p = event.getPlayer();
        double v = event.getFrom().toVector().distance(event.getTo().toVector());
        if(isEnabled() == false){
            return;
        }

        if((event.getFrom().getY() < event.getTo().getY()) || (event.getFrom().getY() > event.getTo().getY())){
            return;
        }

        if(p.isBlocking() == true){
            if((v > 0.269) || (v > 0.21 && v < 0.219)){
                //p.sendMessage(v + "");
                if(Flags.containsKey(p)){
                    Flags.put(p, Flags.get(p)+1);
                }else{
                    Flags.put(p, 1);
                }
                if(Flags.get(p) > 4){
                    Flag(p, Posiblity.Certain, Flags.get(p));
                }else{
                    Flag(p, Posiblity.Possibly, Flags.get(p));
                }
                p.teleport(event.getFrom());
            }
        }

    }

}
