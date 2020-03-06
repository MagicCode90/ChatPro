package io.chatpro.mc90.plugin.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import io.chatpro.mc90.plugin.ChatPro;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class joinleave implements Listener {

	ChatPro cp = ChatPro.getPlugin(ChatPro.class);

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		// player join event

		Player p = e.getPlayer();

		String prefix = PermissionsEx.getUser(p).getPrefix();
		String name = p.getName();
		String displayname = p.getDisplayName();
		String suffix = PermissionsEx.getUser(p).getSuffix();

		String join = cp.getConfig().getString("Messages.PlayerEvents.Join-Msg");
		join = join.replaceAll("&", "§");
		join = join.replaceAll("%player%", name);
		join = join.replaceAll("%displayname%", displayname);
		join = join.replaceAll("%prefix%", prefix);
		join = join.replaceAll("%suffix%", suffix);

		if (cp.getConfig().getString("Messages.PlayerEvents.Join-Msg.join").equals("true")) {
			e.setJoinMessage(join);
		} else if (cp.getConfig().getString("Messages.PlayerEvents.Join-Msg.join").equals("false")) {
			e.setJoinMessage(null);
		}

		if (!(p.hasPlayedBefore())) {
			// player hasn't played before

			// first welcome join
			String fjoin = cp.getConfig().getString("Messages.PlayerEvents.FirstJoin-Msg");
			fjoin = fjoin.replaceAll("&", "§");
			fjoin = fjoin.replaceAll("%player%", name);
			fjoin = fjoin.replaceAll("%displayname%", displayname);
			fjoin = fjoin.replaceAll("%prefix%", prefix);
			fjoin = fjoin.replaceAll("%suffix%", suffix);

			if (cp.getConfig().getString("Messages.PlayerEvents.Join-Msg.join").equals("true")) {
				Bukkit.getServer().broadcastMessage(fjoin);
			} else if (cp.getConfig().getString("Messages.PlayerEvents.Join-Msg.join").equals("false")) {
				// disable & do nothing!

			}
		}

	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		String prefix = PermissionsEx.getUser(p).getPrefix();
		String name = p.getName();
		String displayname = p.getDisplayName();
		String suffix = PermissionsEx.getUser(p).getSuffix();

		String quit = cp.getConfig().getString("Messages.PlayerEvents.Leave-Msg");
		quit = quit.replaceAll("&", "§");
		quit = quit.replaceAll("%player%", name);
		quit = quit.replaceAll("%displayname%", displayname);
		quit = quit.replaceAll("%prefix%", prefix);
		quit = quit.replaceAll("%suffix%", suffix);

		if (cp.getConfig().getString("Messages.PlayerEvents.Leave-Msg.leave").equals("true")) {
			e.setQuitMessage(quit);
		} else if (cp.getConfig().getString("Messages.PlayerEvents.Leave-Msg.leave").equals("false")) {
			e.setQuitMessage(null);
		}

	}

}