package me.superischroma.disguise.vanish;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args) 
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "Only in-game users are allowed to execute this command!");
            return true;
        }
        Player playerSender = (Player) sender;
        if (!sender.isOp())
        {
            sender.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }
        
        if (VanishManager.isVanished(playerSender))
        {
            VanishManager.unvanish(playerSender);
            sender.sendMessage(ChatColor.GRAY + "You are no longer vanished.");
            return true;
        }
        VanishManager.vanish(playerSender);
        sender.sendMessage(ChatColor.GRAY + "You are now vanished.");
        return true;
    }
}
