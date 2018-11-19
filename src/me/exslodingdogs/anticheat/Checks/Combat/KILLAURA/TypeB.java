package me.exslodingdogs.anticheat.Checks.Combat.KILLAURA;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class TypeB extends Check implements Listener {

    public TypeB(){
        super(CheckType.COMBAT, "KILLAURA(TypeB)", true);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){
        if(isEnabled() == false){
            return;
        }
        if(!(event.getDamager() instanceof Player && event.getEntity() instanceof Player)){
            return;
        }
        

    }


}
