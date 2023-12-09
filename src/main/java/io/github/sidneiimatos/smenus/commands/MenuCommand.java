package io.github.sidneiimatos.smenus.commands;

import io.github.sidneiimatos.smenus.inventory.MenuInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

public class MenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Você não é um player");
            return false;
        }
        Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("menu")) {
            new MenuInventory(p);
        }
        return false;
    }
}
