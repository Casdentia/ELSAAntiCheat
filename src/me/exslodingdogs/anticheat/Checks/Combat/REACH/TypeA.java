package me.exslodingdogs.anticheat.Checks.Combat.REACH;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Possibility;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class TypeA extends Check implements Listener {

    public TypeA(){
        super(CheckType.COMBAT, "REACH(TypeA)", true);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){
        if(isEnabled() == false){
            return;
        }
        if(!(event.getDamager() instanceof Player)){
            return;
        }
        Player p = (Player) event.getDamager();
        Entity t = event.getEntity();
        //p.sendMessage(p.getLocation().toVector().distance(t.getLocation().toVector()) + "");
        if(p.getLocation().toVector().distance(t.getLocation().toVector()) > 3.7 && me.exslodingdogs.anticheat.Checks.Combat.KillAura.TypeA.getPing(p) < 400){
            flag(p, Possibility.CERTAIN, 1);
        }

    }


}
