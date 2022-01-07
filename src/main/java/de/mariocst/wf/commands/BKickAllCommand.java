package de.mariocst.wf.commands;

import de.mariocst.wf.MarioMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BKickAllCommand extends Command {
    public BKickAllCommand() {
        super("bkickall", "mario.bungee.bkickall", "bka");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer player)) {
            for (ProxiedPlayer t : MarioMain.getInstance().getProxy().getPlayers()) {
                if (t.hasPermission("mario.bungee.bkickall.bypass") || t.hasPermission("mario.bungee.*") || t.hasPermission("*")) continue;

                if (args.length == 0) {
                    t.disconnect(new TextComponent("Kicked by Admin"));
                }
                else {
                    StringBuilder msg = new StringBuilder();
                    for (String arg : args) {
                        msg.append(arg).append(" ");
                    }

                    t.disconnect(new TextComponent(msg.toString()));
                }
            }
            return;
        }

        if (player.hasPermission("mario.bungee.bkickall") || player.hasPermission("mario.bungee.*") || player.hasPermission("*")) {
            for (ProxiedPlayer t : MarioMain.getInstance().getProxy().getPlayers()) {
                if (t == player || t.hasPermission("mario.bungee.bkickall.bypass") || t.hasPermission("mario.bungee.*") || t.hasPermission("*")) continue;

                if (args.length == 0) {
                    t.disconnect(new TextComponent("Kicked by Admin"));
                }
                else {
                    StringBuilder msg = new StringBuilder();
                    for (String arg : args) {
                        msg.append(arg).append(" ");
                    }

                    t.disconnect(new TextComponent(msg.toString()));
                }
            }
        }
        else {
            player.sendMessage(new TextComponent(MarioMain.getPrefix() + ChatColor.RED + "Keine Rechte!"));
        }
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        if (sender instanceof ProxiedPlayer) {
            return sender.hasPermission("mario.bungee.bkickall") || sender.hasPermission("mario.bungee.*") || sender.hasPermission("*");
        }
        else {
            return true;
        }
    }
}
