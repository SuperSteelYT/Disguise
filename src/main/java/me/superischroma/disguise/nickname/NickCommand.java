package me.superischroma.disguise.nickname;

import me.superischroma.disguise.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args) 
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "Only in-game users are allowed to execute this command!");
            return true;
        }
        if (args.length > 1 || args.length < 1)
        {
            return false;
        }
        Player playerSender = (Player) sender;
        if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("off"))
            {
                if (!NickManager.isNicked(playerSender))
                {
                    sender.sendMessage(ChatColor.GRAY + "You are not nicked.");
                    return true;
                }
                NickManager.unnick(playerSender);
                sender.sendMessage(ChatColor.GRAY + "You are no longer nicked.");
                return true;
            }
        }
        String nick = args[0];
        nick = Util.colorize(nick);
        NickManager.unnick(playerSender);
        NickManager.nick(playerSender, nick);
        sender.sendMessage(ChatColor.GRAY + "Your nick has now been set to \"" + nick + ChatColor.GRAY + "\"");
        return true;
    }
}
