package me.exslodingdogs.anticheat.Checks.block.Scaffold;

import me.exslodingdogs.anticheat.AntiCheat;
import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckResult;
import me.exslodingdogs.anticheat.Checks.CheckType;
import org.bukkit.entity.Player;

public class TypeA extends Check{
    private AntiCheat ac = AntiCheat.getInstance();
    public TypeA(){
        super(CheckType.BLOCK, "Scaffold(TypeA)", true);
    }

    public static CheckResult runcheck(Player player){

        if(player.getLocation().getPitch() < 70 && !player.getItemInHand().getType().isBlock()){
            return CheckResult.FAIL;
        }
        return CheckResult.PASS;
    }


}
