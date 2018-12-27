package cgnpunishments.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.BanList.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Unban implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("unban")) {
			if (sender.hasPermission("cgnpunishments.unban")) {
				if (args.length != 1) {
					sender.sendMessage("§6CGNPunishments§8:§c Correct usage: /unban <nickname>");
				} else {
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					if (target == null) {
						sender.sendMessage("§6CGNPunishments§8: §cCould not find that player (OfflinePlayer). Did you write it correctly?");
						return false;
					}
					Bukkit.getBanList(Type.NAME).pardon(target.getName());
					Bukkit.broadcastMessage("§6CGNPunishments§8: §7"+target.getName()+" was unbanned by "+sender.getName()+".");
					sender.sendMessage("§6CGNPunishments§8:§a The player "+target.getName()+" was unbanned from this server.");
				}
			} else {
				sender.sendMessage("§6CGNPunishments§8:§c Sorry, your permission level is too low.");
			}
		}
		return false;
	}

}
