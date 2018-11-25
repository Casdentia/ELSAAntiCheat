package me.exslodingdogs.anticheat.Checks.Combat.KILLAURA;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Posiblity;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;


public class TypeA extends Check implements Listener {

    public TypeA(){
        super(CheckType.COMBAT, "KILLAURA(TypeA)", true);
    }

    HashMap<Player, Integer> Level = new HashMap<>();


    @EventHandler
    public void oncheck(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)){
            return;
        }

        Player p = (Player) event.getDamager();
        Entity e = event.getEntity();

        Vector el = e.getLocation().toVector().subtract(p.getEyeLocation().toVector()).normalize();
        double ed = el.dot(p.getLocation().getDirection());

        if (ed > 0.99969) {
            if(Level.containsKey(p)){
                Level.put(p, Level.get(p)+1);
            }else{
                Level.put(p, 1);
            }
            event.setCancelled(true);
            Flag(p, Posiblity.Possibly, Level.get(p));
        }
    }

}
