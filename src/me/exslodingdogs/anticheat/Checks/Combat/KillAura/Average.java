package me.exslodingdogs.anticheat.Checks.Combat.KillAura;

import me.exslodingdogs.anticheat.AntiCheat;
import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckResult;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Posiblity;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;

public class Average extends Check implements Listener {

    public Average(){
        super(CheckType.COMBAT, "KILLAURA(Average)");
    }
    public static HashMap<Player, Integer> hps = new HashMap<>();
    public static HashMap<Player, Integer> cps = new HashMap<>();
    private static HashMap<Player, Integer> flagged = new HashMap<>();
    private static AntiCheat ac = AntiCheat.getPlugin(AntiCheat.class);
    @EventHandler
    public void onhit(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player){
            Player p = (Player) event.getDamager();
            if(hps.containsKey(p)){
                hps.put(p, hps.get(p)+1);
            }else{
                hps.put(p, 1);
            }

            if(!cps.containsKey(p)){
                cps.put(p, 1);
            }
            if(getResult() == CheckResult.Fail){
                Flag(p, Posiblity.Certain, 1);
            }
        }
    }
    public static int getPing(Player p) { CraftPlayer cp = (CraftPlayer) p; EntityPlayer ep = cp.getHandle(); return ep.ping; }
    public static void Check(Player player){
        double r = hps.get(player)/cps.get(player);
        //player.sendMessage(r + "");
        if((hps.get(player) == cps.get(player) || cps.get(player) > 3) && getPing(player) <= 300){
            if(flagged.containsKey(player)){
                flagged.put(player, flagged.get(player)+1);
            }else{
                flagged.put(player, 1);
            }
            for(Player op :Bukkit.getOnlinePlayers()){
                if(op.hasPermission("LAC.alerts")){
                    op.sendMessage(cc(prefix + "&c" + player.getName() + " &7failed &eKILLAURA(Average) &7[&cPosiblity: Possible&7][&cLVLl:" + flagged.get(player) + "&7]"));
                }
            }
        }
        if(r >= 17.0 && getPing(player) <= 300){
            if(flagged.containsKey(player)){
                flagged.put(player, flagged.get(player)+1);
            }else{
                flagged.put(player, 1);
            }
            for(Player op :Bukkit.getOnlinePlayers()){
                if(op.hasPermission("LAC.alerts")){
                    op.sendMessage(cc(prefix + "&c" + player.getName() + " &7failed &eKILLAURA(Average) &7[&cPosiblity: Certain&7][&cLVLl:" + flagged.get(player) + "&7]"));
                }
            }
        }

    }

}
