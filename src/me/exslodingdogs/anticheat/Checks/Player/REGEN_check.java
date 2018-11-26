package me.exslodingdogs.anticheat.Checks.Player;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Possibility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import java.util.HashMap;

public class REGEN_check extends Check implements Listener {

    public REGEN_check(){
        super(CheckType.OTHER, "REGEN", false);
    }
    public static HashMap<Player, Integer> timespersec = new HashMap<>();
    public static HashMap<Player, Integer> flagged = new HashMap<>();
    static REGEN_check instance = new REGEN_check();
    public static REGEN_check getInstance(){
        return instance;
    }

    @EventHandler
    public void onRegen(EntityRegainHealthEvent event){
        if(isEnabled() == false){
            return;
        }
        if(event.getEntity() instanceof Player){
            Player p = (Player) event.getEntity();
            if(timespersec.containsKey(p)){
                timespersec.put(p, timespersec.get(p)+1);
            }else{
                timespersec.put(p, 1);
            }
            //p.sendMessage("Regen amount : " + event.getAmount());
        }
    }
    public void result(Player player){
        if(timespersec.get(player) >= 3){
            if(flagged.containsKey(player)){
                flagged.put(player, flagged.get(player)+1);
            }else{
                flagged.put(player, 1);
            }
            if(flagged.get(player) > 3){
                Flag(player, Possibility.CERTAIN, flagged.get(player));
            }else{
                Flag(player, Possibility.POSSIBLE, flagged.get(player));
            }

        }
    }
}
