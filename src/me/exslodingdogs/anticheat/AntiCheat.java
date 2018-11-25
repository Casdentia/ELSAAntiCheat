package me.exslodingdogs.anticheat;

import me.exslodingdogs.anticheat.Checks.Combat.KillAura.TypeA;
import me.exslodingdogs.anticheat.Checks.Movement.NOFALL_check;
import me.exslodingdogs.anticheat.Checks.Movement.NOSLOW_check;
import me.exslodingdogs.anticheat.Checks.Movement.SPEED_check;
import me.exslodingdogs.anticheat.Checks.Player.REGEN_check;
import me.exslodingdogs.anticheat.Checks.block.FastPlaceCheck;
import me.exslodingdogs.anticheat.Commands.Elsa_Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiCheat extends JavaPlugin implements Listener {
    static AntiCheat instance;
    @Override
    public void onEnable(){

        getCommand("elsa").setExecutor(new Elsa_Command());

        runTimedCheck();
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new NOFALL_check(), this);
        Bukkit.getPluginManager().registerEvents(new SPEED_check(), this);
        Bukkit.getPluginManager().registerEvents(new NOSLOW_check(), this);
        Bukkit.getPluginManager().registerEvents(new REGEN_check(), this);
        Bukkit.getPluginManager().registerEvents(new TypeA(), this);
        Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Combat.KILLAURA.TypeB(), this);
        Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Movement.FLY.TypeA(), this);
        Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Movement.FLY.TypeB(), this);
        Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Movement.FLY.TypeC(), this);
        Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Combat.REACH.TypeA(), this);
        Bukkit.getPluginManager().registerEvents(new FastPlaceCheck(), this);
    }

    public static AntiCheat getInstance(){
        return instance;
    }


    public void runTimedCheck(){
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(Player op : Bukkit.getOnlinePlayers()){
                    if (REGEN_check.timespersec.containsKey(op)) {
                        // op.sendMessage(REGEN_check.timespersec.get(op) + "");
                        REGEN_check.getInstance().result(op);
                        REGEN_check.timespersec.remove(op);
                    }

                }
            }
        }, 0,20);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(Player op : Bukkit.getOnlinePlayers()){
                    if(TypeA.cps.containsKey(op) && TypeA.hps.containsKey(op)){
                        TypeA.Check(op);
                        TypeA.cps.remove(op);
                        TypeA.hps.remove(op);
                    }

                }
            }
        }, 0,20*2);
    }

}
