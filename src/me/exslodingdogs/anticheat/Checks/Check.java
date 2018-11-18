package me.exslodingdogs.anticheat.Checks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Check {

    public static String prefix = "&4&lELSA&r&7>> &7";

    protected CheckType type;
    protected String nameofhack;
    private Posiblity posiblity;
    private CheckResult result;
    static Check instance;
    public static Check getInstance(){
        return instance;
    }
    public Check(CheckType type, String nameofhack){
        this.type = type;
        this.nameofhack = nameofhack;
    }

    public CheckType getType(){
        return this.type;
    }

    public CheckResult getResult(){return this.result;}
    public CheckResult setResault(CheckResult resault){return this.result = resault;}

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
           if(op.hasPermission("LAC.alerts")){
               op.sendMessage(cc(prefix + "&c" + player.getName() + " &7failed &e" + getHack() + " &7[&cPosiblity: " + getPosiblity() + "&7][&cLVLl:" + alerts + "&7]"));
           }
       }
    }

}
