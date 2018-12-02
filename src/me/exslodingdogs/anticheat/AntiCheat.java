package me.exslodingdogs.anticheat;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckResult;
import me.exslodingdogs.anticheat.Checks.Movement.NOFALL_check;
import me.exslodingdogs.anticheat.Checks.Movement.NOSLOW_check;
import me.exslodingdogs.anticheat.Checks.Player.REGEN_check;
import me.exslodingdogs.anticheat.Checks.block.FastPlaceCheck;
import me.exslodingdogs.anticheat.Commands.Elsa_Command;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiCheat extends JavaPlugin implements Listener {
    static AntiCheat instance;

    @Override
    public void onEnable() {



        setupProcalLib();

        protocolManager.addPacketListener(new PacketAdapter(this, packetsToCheck) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                //System.out.println("RECEIVING (type: " + packet.getType() + ")");

                if(me.exslodingdogs.anticheat.Checks.block.Scaffold.TypeA.runcheck(event.getPlayer()) == CheckResult.FAIL){
                    for(Player op :Bukkit.getOnlinePlayers()){
                        if(op.hasPermission("elsa.alerts")){
                            op.sendMessage(Check.cc(Check.prefix + "&c" + event.getPlayer().getName() + " &7failed &eScaffold(TypeA) &8[&cPosiblity: POSIBLE&8] [&cLVL: 1&8]"));
                        }
                    }
                }
            }
            @Override
            public void onPacketSending(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                System.out.println("SENDING (type: " + packet.getType() + ")");
            }
        });


        getCommand("elsa").setExecutor(new Elsa_Command());

        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8(&6&lELSA&r&8) &7You are using Elsa v2.0"));
        runTimedCheck();

        //Player Checks
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new NOFALL_check(), this);
        Bukkit.getPluginManager().registerEvents(new NOSLOW_check(), this);
        Bukkit.getPluginManager().registerEvents(new REGEN_check(), this);
        //Bukkit.getPluginManager().registerEvents(new Derp(), this); not finished yet


        //KILlAURA CHECKS
        Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Combat.KILLAURA.TypeA(), this);
        Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Combat.KILLAURA.TypeB(), this);

        //FLY CHECKS
        Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Movement.FLY.TypeA(), this);
        Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Movement.FLY.TypeB(), this);
        Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Movement.FLY.TypeC(), this);

        //SPEED CHECKS
        Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Movement.SPEED.TypeA(), this);
        Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Movement.SPEED.TypeB(), this);

        //REACH CHECKS
        Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Combat.REACH.TypeA(), this);

        //FastPlace CHECK
        Bukkit.getPluginManager().registerEvents(new FastPlaceCheck(), this);
    }
    public static AntiCheat getInstance(){
        return instance;
    }

    public static ProtocolManager protocolManager;
    public void setupProcalLib(){
        protocolManager = ProtocolLibrary.getProtocolManager();

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

    final PacketType[] packetsToCheck = {
            PacketType.Play.Client.BLOCK_PLACE
    };

    @Override
    public void  onDisable(){
        for(Player op: Bukkit.getOnlinePlayers()){
            op.kickPlayer(ChatColor.RED + "Restarting server!");
        }
    }

}
