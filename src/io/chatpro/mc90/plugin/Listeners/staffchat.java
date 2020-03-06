package io.chatpro.mc90.plugin.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import io.chatpro.mc90.plugin.ChatPro;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class staffchat implements Listener {

	ChatPro cp = ChatPro.getPlugin(ChatPro.class);

	@EventHandler
	public void staffchatevent(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		String prefix = PermissionsEx.getUser(p).getPrefix();
		String name = p.getName();
		String displayname = p.getDisplayName();
		String suffix = PermissionsEx.getUser(p).getSuffix();
		String message = e.getMessage();

		String scformat = cp.getConfig().getString("Messages.StaffChat.Format");
		scformat = scformat.replaceAll("&", "§");
		scformat = scformat.replaceAll("%player%", name);
		scformat = scformat.replaceAll("%displayname%", displayname);
		scformat = scformat.replaceAll("%prefix%", prefix);
		scformat = scformat.replaceAll("%suffix%", suffix);
		scformat = scformat.replaceAll("%message%", message);

		for (Player everyone : Bukkit.getOnlinePlayers()) {
			if (everyone.hasPermission("cp.staffchat.see")) {
				if (e.getMessage().startsWith(cp.getConfig().getString("Messages.StaffChat.Chat-Symbol"))) {
					everyone.sendMessage(scformat);
					e.setCancelled(true);
				}
			} else {
				// ...
			}
		}

	}

}