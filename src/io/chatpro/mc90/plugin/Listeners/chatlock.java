package io.chatpro.mc90.plugin.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import io.chatpro.mc90.plugin.ChatPro;
import io.chatpro.mc90.plugin.Commands.chatproCMD;

public class chatlock implements Listener {

	ChatPro cp = ChatPro.getPlugin(ChatPro.class);
	chatproCMD cl = new chatproCMD();

	@EventHandler
	public void chat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		if (cl.isLocked == true) {
			if (p.hasPermission("chatpro.chatlock.bypass")) {
				e.setCancelled(false);
				return;
			} else {
				e.setCancelled(true);
			}
		} else {
			e.setCancelled(false);
		}
	}

}