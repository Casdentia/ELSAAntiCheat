package me.exslodingdogs.anticheat.Checks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Check {

    public static String prefix = "&8[&6&lElsa&r&8] &7";

    protected CheckType type;
    protected String hackName;
    private Possibility possibility;
    private CheckResult result;
    private boolean enabled;

    public Check(CheckType type, String hackName, boolean enabled){
        this.type = type;
        this.hackName = hackName;
        this.enabled = enabled;
    }

    public Boolean isEnabled(){
        return enabled;
    }

    public CheckResult getResult(){return this.result;}
    public CheckResult setResult(CheckResult resault){return this.result = resault;}

    public String getHack(){
        return this.hackName;
    }

    public Possibility getPossibility(){return possibility;}

    public static String cc(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public void flag(Player player, Possibility possibility, int alerts){
        this.possibility = possibility;
       for(Player op :Bukkit.getOnlinePlayers()){
           if(op.hasPermission("elsa.alerts")){
               op.sendMessage(cc(prefix + "&c" + player.getName() + " &7failed &e" + getHack() + " &7[&cPosiblity: " + getPossibility() + "&7][&cLVLl:" + alerts + "&7]"));
           }
       }
    }

}
