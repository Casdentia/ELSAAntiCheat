package me.exslodingdogs.anticheat.Checks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Check {

    public static String prefix = "&8(&6&lELSA&r&8) &7";

    protected CheckType type;
    protected String nameofhack;
    private Possibility possibility;
    private CheckResult result;
    private boolean enabled;

    public Check(CheckType type, String nameofhack, boolean enabled){
        this.type = type;
        this.nameofhack = nameofhack;
        this.enabled = enabled;
    }

    public Boolean isEnabled(){
        return enabled;
    }

    public CheckResult getResult(){return this.result;}

    public String getHack(){
        return this.nameofhack;
    }

    public Possibility getPossibility(){return possibility;}

    public static String cc(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public void Flag(Player player, Possibility possibility, int alerts){
        this.possibility = possibility;
       for(Player op :Bukkit.getOnlinePlayers()){
           if(op.hasPermission("elsa.alerts")){
               op.sendMessage(cc(prefix + "&c" + player.getName() + " &7failed &e" + getHack() + " &8[&cPosiblity: " + getPossibility() + "&8] [&cLVL: " + alerts + "&8]"));
           }
       }
    }

}
