package io.chatpro.mc90.plugin.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import io.chatpro.mc90.plugin.ChatPro;
import net.md_5.bungee.api.ChatColor;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class format implements Listener {

	ChatPro cp = ChatPro.getPlugin(ChatPro.class);

	@EventHandler
	public void formatchat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		// holders
		// prefix
		// chat
		// placeholders
		// message

		// format: %prefix% %player% %suffix%: %message%

		String prefix = PermissionsEx.getUser(p).getPrefix();
		String name = p.getName();
		String displayname = p.getDisplayName();
		String suffix = PermissionsEx.getUser(p).getSuffix();
		String message = e.getMessage();

		String format = cp.getConfig().getString("Messages.Format");
		format = format.replaceAll("&", "§");
		format = format.replaceAll("%player%", name);
		format = format.replaceAll("%displayname%", displayname);
		format = format.replaceAll("%prefix%", prefix);
		format = format.replaceAll("%suffix%", suffix);
		format = format.replaceAll("%message%", message);

		if (p.hasPermission("cp.chat.color")) {
			e.setFormat(ChatColor.translateAlternateColorCodes('&', format));
		} else {
			e.setFormat(format);
		}
	}
}
