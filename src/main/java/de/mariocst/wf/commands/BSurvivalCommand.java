package de.mariocst.wf.commands;

import de.mariocst.wf.MarioMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BSurvivalCommand extends Command {
    public BSurvivalCommand() {
        super("bsurvival", "mario.bungee.bcb", "bs");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer player)) {
            sender.sendMessage(new TextComponent("Bitte führe den Command InGame aus!"));
            return;
        }

        if (player.hasPermission("mario.bungee.bsurvival") || player.hasPermission("mario.bungee.*") || player.hasPermission("*")) {
            if (player.getServer().getInfo().getName().equalsIgnoreCase(MarioMain.getInstance().getConfig().getSurvivalServer())) {
                player.sendMessage(new TextComponent(MarioMain.getPrefix() + ChatColor.RED + "Du bist bereits mit dem Survival Server verbunden!"));
            }
            else {
                ServerInfo cb = ProxyServer.getInstance().getServerInfo(MarioMain.getInstance().getConfig().getSurvivalServer());
                player.connect(cb);
            }
        }
        else {
            player.sendMessage(new TextComponent(MarioMain.getPrefix() + ChatColor.RED + "Keine Rechte!"));
        }
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        if (sender instanceof ProxiedPlayer) {
            return sender.hasPermission("mario.bungee.bsurvival") || sender.hasPermission("mario.bungee.*") || sender.hasPermission("*");
        }
        else {
            return true;
        }
    }
}
