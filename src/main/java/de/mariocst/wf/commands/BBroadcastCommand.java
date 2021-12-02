package de.mariocst.wf.commands;

import de.mariocst.wf.MarioMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BBroadcastCommand extends Command {
    public BBroadcastCommand() {
        super("bbroadcast", "mario.bungee.bbroadcast", "bbc");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        String usage = MarioMain.getPrefix() + ChatColor.RED + "Usage: /bbc <Text>";

        if (!(sender instanceof ProxiedPlayer player)) {
            try {
                if (args.length >= 1) {
                    StringBuilder msg = new StringBuilder();
                    for (String arg : args) {
                        msg.append(arg).append(" ");
                    }

                    MarioMain.getInstance().getProxy().broadcast(new TextComponent(MarioMain.getPrefix() + msg));
                }
                else {
                    sender.sendMessage(new TextComponent(usage));
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(new TextComponent(usage));
            }
            return;
        }

        if (player.hasPermission("mario.bungee.bbroadcast") || player.hasPermission("mario.bungee.*") || player.hasPermission("*")) {
            try {
                if (args.length >= 1) {
                    StringBuilder msg = new StringBuilder();
                    for (String arg : args) {
                        msg.append(arg).append(" ");
                    }

                    MarioMain.getInstance().getProxy().broadcast(new TextComponent(MarioMain.getPrefix() + msg));
                }
                else {
                    player.sendMessage(new TextComponent(usage));
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(new TextComponent(usage));
            }
        }
        else {
            player.sendMessage(new TextComponent(MarioMain.getPrefix() + ChatColor.RED + "Keine Rechte!"));
        }
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        if (sender instanceof ProxiedPlayer) {
            return sender.hasPermission("mario.bungee.bbroadcast") || sender.hasPermission("mario.bungee.*") || sender.hasPermission("*");
        }
        else {
            return true;
        }
    }
}
