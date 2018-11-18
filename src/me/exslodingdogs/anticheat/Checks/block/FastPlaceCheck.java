package me.exslodingdogs.anticheat.Checks.block;

import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckResult;
import me.exslodingdogs.anticheat.Checks.CheckType;
import me.exslodingdogs.anticheat.Checks.Posiblity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class FastPlaceCheck extends Check implements Listener {

    public FastPlaceCheck(CheckType type, String nameofhack) {
        super(type, nameofhack);
    }

    @Override
    public CheckType getType() {
        return super.getType();
    }

    @Override
    public CheckResult getResult() {
        return super.getResult();
    }

    @Override
    public CheckResult setResault(CheckResult resault) {
        return super.setResault(resault);
    }

    @Override
    public String getHack() {
        return super.getHack();
    }

    @Override
    public Posiblity getPosiblity() {
        return super.getPosiblity();
    }

    @Override
    public void Flag(Player player, Posiblity posiblity, int alerts) {
        super.Flag(player, posiblity, alerts);
    }
}
