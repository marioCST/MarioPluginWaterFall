package de.mariocst.wf.listener;

import de.mariocst.wf.MarioMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PostLoginListener implements Listener {
    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();

        for (ProxiedPlayer online : MarioMain.getInstance().getProxy().getPlayers()) {
            online.sendMessage(new TextComponent(MarioMain.getPrefix() + ChatColor.GREEN + ChatColor.BOLD + "Der Spieler " + ChatColor.GOLD + ChatColor.BOLD + player.getName() + ChatColor.GREEN + ChatColor.BOLD + " ist dem Netzwerk beigetreten!"));
        }

        MarioMain.getInstance().log(MarioMain.getPrefix() + ChatColor.GREEN + ChatColor.BOLD + "Der Spieler " + ChatColor.GOLD + ChatColor.BOLD + player.getName() + ChatColor.GREEN + ChatColor.BOLD + " ist dem Netzwerk beigetreten!");
    }
}
