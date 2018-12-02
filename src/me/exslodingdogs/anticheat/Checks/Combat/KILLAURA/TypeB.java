package me.exslodingdogs.anticheat.Checks.Combat.KILLAURA;

import javafx.geometry.Pos;
import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Possibility;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class TypeB extends Check implements Listener {

    public TypeB(){
        super(CheckType.COMBAT, "KILLAURA(TypeB)", true);
    }
    HashMap<Player, Integer> Level = new HashMap<>();
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){
        if(isEnabled() == false){
            return;
        }
        if(!(event.getDamager() instanceof Player)){
            return;
        }

        Player p = (Player) event.getDamager();
        Entity e = event.getEntity();

        Vector el = e.getLocation().toVector().subtract(p.getEyeLocation().toVector()).normalize();
        double ed = el.dot(p.getLocation().getDirection());


        //p.sendMessage("e : " + ed);
        if (ed > 0.99969) {
            if(Level.containsKey(p)){
                Level.put(p, Level.get(p)+1);
            }else{
                Level.put(p, 1);
            }
            event.setCancelled(true);
            flag(p, Possibility.POSSIBLE, Level.get(p));
        }





    }


}
