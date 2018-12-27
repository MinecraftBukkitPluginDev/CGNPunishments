package cgnpunishments.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ban implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ban")) {
			if (sender.hasPermission("cgnpunishments.ban")) {
				if (args.length < 2) {
					sender.sendMessage("§6CGNPunishments§8: §cCorrect usage: /ban <nickname> <message ..");
				} else {
					String message = "§c";
					Player target = Bukkit.getPlayer(args[0]);
					OfflinePlayer offline_target;
					for (int i = 1; i < args.length; i ++) {
						message = message + " " + args[i];
					} 
					if (target == null) {
						sender.sendMessage("§6CGNPunishments§8: §cThe player is currently offline. Trying to find the OfflinePlayer ..");
						offline_target = Bukkit.getOfflinePlayer(args[0]);
						if (offline_target == null) {
							sender.sendMessage("§6CGNPunishments§8: §cCould not find the offline player. Did you write it correctly?");
							return false;
						} else {
							String bumper = StringUtils.repeat("\n", 28);
							Bukkit.getBanList(Type.NAME).addBan(offline_target.getName(), bumper+"§4You can't join this server\n\n"+"§8§8####################################################################################\n\n"+"§c"+message+"\n\n§8Banned by: §c"+sender.getName()+"\n§8Ban Type:§c Permanent\n§8Banned:§c When offline\n§8Appeal:§c cgns.email@client.domain"+"\n\n§8####################################################################################\n"+bumper, null, null);
							Bukkit.broadcastMessage("§6CGNPunishments§8:§7 "+offline_target.getName()+" was permanently banned by "+sender.getName()+" for §c"+message);
							sender.sendMessage("§6CGNPunishments§8:§a The player "+offline_target.getName()+" was banned from this server. Make sure you considered all the /rules before banning. If this was a mistake /unban the player as quick as possible.");
							return true;
						}
					}
					String bumper = StringUtils.repeat("\n", 15);
					Bukkit.getBanList(Type.NAME).addBan(target.getDisplayName(), bumper+"§4You can't join this server\n\n"+"§8####################################################################################\n\n"+"§c"+message+"\n\n§8Banned by: §c"+sender.getName()+"\n§8Ban Type:§c Permanent\n§8Banned:§c When online\n§8Appeal:§c cgns.email@client.domain"+"\n\n§8################################################################\n"+bumper, null, null);
					target.kickPlayer("§8You have been banned from the server for:\n"+message+"\n§8By:§c "+sender.getName()+"\n§8Type:§c Permanent");
					Bukkit.broadcastMessage("§6CGNPunishments§8:§7 "+target.getDisplayName()+" was permanently banned by "+sender.getName()+" for §c"+message);
					sender.sendMessage("§6CGNPunishments§8:§a The player "+target.getDisplayName()+" was banned from this server. Make sure you considered all the /rules before banning. If this was a mistake /unban the player as quick as possible.");   
				}
			} else {
				sender.sendMessage("§6CGNPunishments§8:§c Sorry, your permission level is too low.");
			}
		}
		return false;
	}

}
