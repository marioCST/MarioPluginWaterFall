package de.mariocst.wf.commands;

import de.mariocst.wf.MarioMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BSetPrefixCommand extends Command {
    public BSetPrefixCommand() {
        super("bsetprefix", "mario.bungee.setprefix", "bsp");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        StringBuilder msg = new StringBuilder();

        String prefix;
        if (!(sender instanceof ProxiedPlayer player)) {
            if (args.length >= 1) {
                for (String arg : args) {
                    msg.append(arg).append(" ");
                }
                prefix = msg.toString();

                MarioMain.getInstance().log("Der Prefix ist nun: " + prefix);
                MarioMain.setPrefix(prefix.replaceAll("&", "§"));
                MarioMain.getInstance().saveConfiguration();
            }
            else {
                MarioMain.getInstance().warning("Usage: /bsetprefix <Prefix>");
            }
            return;
        }

        if (player.hasPermission("mario.bungee.setprefix") || player.hasPermission("mario.bungee.*") || player.hasPermission("*")) {
            if (args.length >= 1) {
                for (String arg : args) {
                    msg.append(arg).append(" ");
                }
                prefix = msg.toString();

                player.sendMessage(new TextComponent(MarioMain.getPrefix() + "Der Prefix ist nun: " + prefix));
                MarioMain.setPrefix(prefix.replaceAll("&", "§"));
                MarioMain.getInstance().saveConfiguration();
            }
            else {
                player.sendMessage(new TextComponent(MarioMain.getPrefix() + ChatColor.RED + "Usage: /bsetprefix <Prefix>"));
            }
        }
        else {
            player.sendMessage(new TextComponent(MarioMain.getPrefix() + ChatColor.RED + "Keine Rechte!"));
        }
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        if (sender instanceof ProxiedPlayer) {
            return sender.hasPermission("mario.bungee.setprefix") || sender.hasPermission("mario.bungee.*") || sender.hasPermission("*");
        }
        else {
            return true;
        }
    }
}
