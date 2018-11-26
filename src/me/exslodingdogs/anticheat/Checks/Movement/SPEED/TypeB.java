package me.exslodingdogs.anticheat.Checks.Movement.SPEED;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TypeB extends Check implements Listener {

    public TypeB(){
        super(CheckType.MOVEMENT, "SPEED(TypeB)", true);
    }
    /*

    This is not finished yet

     */

    @EventHandler
    public void onmove(PlayerMoveEvent event){

    }

}
