package de.mariocst.wf.commands;

import de.mariocst.wf.MarioMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BPingCommand extends Command {
    public BPingCommand() {
        super("bping", "mario.bungee.bping", "bp");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer player)) {
            sender.sendMessage(new TextComponent("Bitte führe den Command InGame aus!"));
            return;
        }

        if (player.hasPermission("mario.bungee.bping") || player.hasPermission("mario.bungee.*") || player.hasPermission("*")) {
            if (args.length == 1) {
                ProxiedPlayer t = MarioMain.getInstance().getProxy().getPlayer(args[0]);

                if (t != null) {
                    player.sendMessage(new TextComponent(MarioMain.getPrefix() + "Ping von " + t.getName() + ": " + t.getPing()));
                }
                else {
                    player.sendMessage(new TextComponent(MarioMain.getPrefix() + ChatColor.RED + "Unbekannter Spieler: " + args[0]));
                }
            }
            else {
                player.sendMessage(new TextComponent(MarioMain.getPrefix() + "Dein Ping: " + player.getPing()));
            }
        }
        else {
            player.sendMessage(new TextComponent(MarioMain.getPrefix() + ChatColor.RED + "Keine Rechte!"));
        }
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        if (sender instanceof ProxiedPlayer) {
            return sender.hasPermission("mario.bungee.bping") || sender.hasPermission("mario.bungee.*") || sender.hasPermission("*");
        }
        else {
            return true;
        }
    }
}
