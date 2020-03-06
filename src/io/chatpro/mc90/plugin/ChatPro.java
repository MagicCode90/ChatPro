package io.chatpro.mc90.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import io.chatpro.mc90.plugin.Commands.chatproCMD;
import io.chatpro.mc90.plugin.Listeners.chatlock;
import io.chatpro.mc90.plugin.Listeners.format;
import io.chatpro.mc90.plugin.Listeners.joinleave;
import io.chatpro.mc90.plugin.Listeners.staffchat;
import net.md_5.bungee.api.ChatColor;

public class ChatPro extends JavaPlugin {

	public PluginDescriptionFile pdf;
	
	@Override
	public void onEnable() {
		// start
		registerCmds();
		registerEvents();
		startConfigs();
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[ChatPro] Plugin enabled. Current Version: " + ChatColor.WHITE + " + pdf.getVersion()");
	}

	@Override
	public void onDisable() {
		// end
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[ChatPro] Plugin disabled.");
	}
	
	public void registerEvents() {
		// events/listeners
		getServer().getPluginManager().registerEvents(new format(), this);
		getServer().getPluginManager().registerEvents(new staffchat(), this);
		getServer().getPluginManager().registerEvents(new joinleave(), this);
		getServer().getPluginManager().registerEvents(new chatlock(), this);
	}
	
	public void registerCmds() {
		// commands
		getCommand("chatpro").setExecutor(new chatproCMD());
	}
	
	public void startConfigs() {
		// configurations
		getConfig().options().copyDefaults(true);
		try {
			saveConfig();
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[ChatPro] Config could not be saved... if you can't fix this or this continues to be an issue, please contact the developer!");
			e.fillInStackTrace();
		}
	}
	
}
