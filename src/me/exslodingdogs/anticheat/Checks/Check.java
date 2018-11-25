package me.exslodingdogs.anticheat.Checks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Check {

    public static String prefix = "&8(&6&lELSA&r&8) &7";

    protected CheckType type;
    protected String nameofhack;
    private Posiblity posiblity;
    private CheckResault resault;
    private boolean enabled;

    public Check(CheckType type, String nameofhack, boolean enabled){
        this.type = type;
        this.nameofhack = nameofhack;
        this.enabled = enabled;
    }

    public Boolean isEnabled(){
        return enabled;
    }

    public CheckResault getResault(){return this.resault;}

    public String getHack(){
        return this.nameofhack;
    }

    public Posiblity getPosiblity(){return posiblity;}

    public static String cc(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public void Flag(Player player, Posiblity posiblity, int alerts){
        this.posiblity = posiblity;
       for(Player op :Bukkit.getOnlinePlayers()){
           if(op.hasPermission("elsa.alerts")){
               op.sendMessage(cc(prefix + "&c" + player.getName() + " &7failed &e" + getHack() + " &8[&cPosiblity: " + getPosiblity() + "&8] [&cLVL: " + alerts + "&8]"));
           }
       }
    }

}
