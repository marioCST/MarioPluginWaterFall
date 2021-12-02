package de.mariocst.wf.commands;

import de.mariocst.wf.MarioMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BConfigCommand extends Command {
    public BConfigCommand() {
        super("bconfig", "mario.bungee.bconfig", "bconfiguration");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer player)) {
            try {
                if (args.length == 1) {
                    switch (args[0].toLowerCase()) {
                        case "save" -> {
                            MarioMain.getInstance().saveConfiguration();
                            MarioMain.getInstance().log("Configs gespeichert!");
                        }
                        case "reload" -> {
                            MarioMain.getInstance().loadConfiguration();
                            MarioMain.getInstance().log("Configs neu geladen!");
                        }
                        default -> MarioMain.getInstance().warning("/config <save|reload>");
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                MarioMain.getInstance().warning("/config <save|reload>");
            }
            return;
        }

        if (player.hasPermission("mario.bungee.bconfig") || player.hasPermission("mario.bungee.*") || player.hasPermission("*")) {
            try {
                if (args.length == 1) {
                    switch (args[0].toLowerCase()) {
                        case "save" -> {
                            MarioMain.getInstance().saveConfiguration();
                            player.sendMessage(new TextComponent(MarioMain.getPrefix() + "Configs gespeichert!"));
                        }
                        case "reload" -> {
                            MarioMain.getInstance().loadConfiguration();
                            player.sendMessage(new TextComponent(MarioMain.getPrefix() + "Configs neu geladen!"));
                        }
                        default -> player.sendMessage(new TextComponent(MarioMain.getPrefix() + "/config <save|reload>"));
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(new TextComponent(MarioMain.getPrefix() + "/config <save|reload>"));
            }
        }
        else {
            player.sendMessage(new TextComponent(MarioMain.getPrefix() + ChatColor.RED + "Keine Rechte!"));
        }
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        if (sender instanceof ProxiedPlayer) {
            return sender.hasPermission("mario.bungee.bconfig") || sender.hasPermission("mario.bungee.*") || sender.hasPermission("*");
        }
        else {
            return true;
        }
    }
}
