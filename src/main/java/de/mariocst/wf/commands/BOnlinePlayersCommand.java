package de.mariocst.wf.commands;

import de.mariocst.wf.MarioMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BOnlinePlayersCommand extends Command {
    public BOnlinePlayersCommand() {
        super("bonlineplayers", "mario.bungee.bonlineplayers", "bop");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer player)) {
            if (MarioMain.getInstance().getProxy().getPlayers().isEmpty()) {
                MarioMain.getInstance().warning("Es ist kein Spieler Online!");
                return;
            }

            MarioMain.getInstance().log("Online Players:");

            for (ProxiedPlayer player1 : MarioMain.getInstance().getProxy().getPlayers()) {
                MarioMain.getInstance().log(player1.getName() + " [" + player1.getServer().getInfo().getName() + "]");
            }
            return;
        }

        if (player.hasPermission("mario.bungee.bonlineplayers") || player.hasPermission("mario.bungee.*") || player.hasPermission("*")) {
            if (MarioMain.getInstance().getProxy().getPlayers().isEmpty()) {
                player.sendMessage(new TextComponent(MarioMain.getPrefix() + ChatColor.RED + "Es ist kein Spieler Online!"));
                return;
            }

            player.sendMessage(new TextComponent(MarioMain.getPrefix() + ChatColor.GOLD + "Online Players:"));

            for (ProxiedPlayer player1 : MarioMain.getInstance().getProxy().getPlayers()) {
                player.sendMessage(new TextComponent(MarioMain.getPrefix() + ChatColor.GOLD + player1.getName() + " [" + player1.getServer().getInfo().getName() + "]"));
            }
        }
        else {
            player.sendMessage(new TextComponent(MarioMain.getPrefix() + ChatColor.RED + "Keine Rechte!"));
        }
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        if (sender instanceof ProxiedPlayer) {
            return sender.hasPermission("mario.bungee.bonlineplayers") || sender.hasPermission("mario.bungee.*") || sender.hasPermission("*");
        }
        else {
            return true;
        }
    }
}
