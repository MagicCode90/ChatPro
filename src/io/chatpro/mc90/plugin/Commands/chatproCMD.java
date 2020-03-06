package io.chatpro.mc90.plugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.chatpro.mc90.plugin.ChatPro;

public class chatproCMD implements CommandExecutor {

	public Boolean isLocked = false;
	ChatPro cp = ChatPro.getPlugin(ChatPro.class);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {

			return true;
		}

		Player p = (Player) sender;

		// no permission - configuration section
		String noperm = cp.getConfig().getString("Messages.No-Permission");
		noperm = noperm.replaceAll("&", "§");
		noperm = noperm.replaceAll("{prefix}", cp.getConfig().getString("Messages.Prefix"));

		if (cmd.getName().equalsIgnoreCase("chatpro")) {
			if (p.hasPermission("chatpro.command") || p.hasPermission("chatpro.*")) {
				if (args.length == 0) {
					p.sendMessage("§6[§e§lChatPro§6] §3Thanks for using chatpro §f" + cp.pdf.getVersion());
					p.sendMessage("§7Execute §e'/chatpro help'§7, this will take you to the help-page!");
					return true;
				} else if (args[0].equalsIgnoreCase("lockchat")) {
					if (p.hasPermission("chatpro.command.lockchat") || p.hasPermission("chatpro.*")) {
						if(args.length == 0) {
							if(isLocked == false) {
								isLocked = true;
								
								String clEnabled = cp.getConfig().getString("Messages.ChatLock.Enabled");
								clEnabled = clEnabled.replaceAll("&", "§");
								clEnabled = clEnabled.replaceAll("{prefix}", cp.getConfig().getString("Messages.Prefix"));
								clEnabled = clEnabled.replaceAll("%sender%", sender.getName());
								Bukkit.getServer().broadcastMessage(clEnabled);
							} else if(isLocked == true) {
								isLocked = false;
								
								String clDisabled = cp.getConfig().getString("Messages.ChatLock.Disabled");
								clDisabled = clDisabled.replaceAll("&", "§");
								clDisabled = clDisabled.replaceAll("{prefix}", cp.getConfig().getString("Messages.Prefix"));
								clDisabled = clDisabled.replaceAll("%sender%", sender.getName());
								Bukkit.getServer().broadcastMessage(clDisabled);
							}
						}
					} else {
						p.sendMessage(noperm);
						return true;
					}
				} else if (args[0].equalsIgnoreCase("help")) {
					if (p.hasPermission("chatpro.command") || p.hasPermission("chatpro.*")) {
						if (args.length == 0) {
							p.sendMessage("§cChatPro - HelpPage [1/1]");
							p.sendMessage("");
							p.sendMessage("§6/chatpro lockchat");
							p.sendMessage("/chatpro help");
							p.sendMessage("§6/chatpro reload");
							p.sendMessage("");
						}
					} else {
						p.sendMessage(noperm);
						return true;
					}
				} else {
					p.sendMessage(noperm);
					return true;
				}
			}
		}
		return false;
	}

}
