package de.mariocst.wf.listener;

import de.mariocst.wf.MarioMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class DisconnectListener implements Listener {
    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();

        for (ProxiedPlayer online : MarioMain.getInstance().getProxy().getPlayers()) {
            online.sendMessage(new TextComponent(MarioMain.getPrefix() + ChatColor.RED + ChatColor.BOLD + "Der Spieler " + ChatColor.GOLD + ChatColor.BOLD + player.getName() + ChatColor.RED + ChatColor.BOLD + " hat das Netzwerk verlassen!"));
        }

        MarioMain.getInstance().log(MarioMain.getPrefix() + ChatColor.RED + ChatColor.BOLD + "Der Spieler " + ChatColor.GOLD + ChatColor.BOLD + player.getName() + ChatColor.RED + ChatColor.BOLD + " hat das Netzwerk verlassen!");
    }
}
