package cgnpunishments;

import org.bukkit.plugin.java.JavaPlugin;

import cgnpunishments.commands.Ban;
import cgnpunishments.commands.Unban;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getCommand("ban").setExecutor(new Ban());
		getCommand("unban").setExecutor(new Unban());
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
