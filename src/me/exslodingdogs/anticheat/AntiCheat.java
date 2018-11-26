package me.exslodingdogs.anticheat;

import me.exslodingdogs.anticheat.Checks.Movement.NOFALL_check;
import me.exslodingdogs.anticheat.Checks.Movement.NOSLOW_check;
import me.exslodingdogs.anticheat.Checks.Player.REGEN_check;
import me.exslodingdogs.anticheat.Commands.Elsa_Command;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiCheat extends JavaPlugin implements Listener {
    static AntiCheat instance;
    @Override
    public void onEnable(){

        getCommand("elsa").setExecutor(new Elsa_Command());
        int i = 20*5;
        while(i > 0){
            i--;
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8(&6&lELSA&r&8) &7Starting checks in " + i));
        }
        if(i == 0){
            runTimedCheck();
            Bukkit.getPluginManager().registerEvents(this, this);
            Bukkit.getPluginManager().registerEvents(new NOFALL_check(), this);
            Bukkit.getPluginManager().registerEvents(new NOSLOW_check(), this);
            Bukkit.getPluginManager().registerEvents(new REGEN_check(), this);

            //KILlAURA CHECKS
            Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Combat.KILLAURA.TypeA(), this);

            //FLY CHECKS
            Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Movement.FLY.TypeA(), this);
            Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Movement.FLY.TypeB(), this);
            Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Movement.FLY.TypeC(), this);

            //SPEED CHECKS
            Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Movement.SPEED.TypeA(), this);

            //REACH CHECKS
            Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Combat.REACH.TypeA(), this);
        }


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

            }
        }, 0,20*2);
    }

}
