package de.mariocst.wf;

import de.mariocst.wf.commands.*;
import de.mariocst.wf.config.Config;
import de.mariocst.wf.listener.*;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public final class MarioMain extends Plugin {
    private static MarioMain marioMain;
    private static String prefix;
    private final PluginManager manager = this.getProxy().getPluginManager();
    private Config config;

    @Override
    public void onLoad() {
        marioMain = this;
    }

    @Override
    public void onEnable() {
        this.listenerRegistration();
        this.commandRegistration();

        this.loadConfiguration();

        prefix = this.config.getPrefix();

        this.log("Mario Plugin WaterFall aktiviert!");
    }

    @Override
    public void onDisable() {
        this.saveConfiguration();

        this.log("Mario Plugin WaterFall deaktiviert!");
    }

    private void listenerRegistration() {
        this.manager.registerListener(this, new DisconnectListener());
        this.manager.registerListener(this, new PostLoginListener());
    }

    private void commandRegistration() {
        this.manager.registerCommand(this, new BBroadcastCommand());
        this.manager.registerCommand(this, new BConfigCommand());
        this.manager.registerCommand(this, new BKickAllCommand());
        this.manager.registerCommand(this, new BLobbyCommand());
        this.manager.registerCommand(this, new BOnlinePlayersCommand());
        this.manager.registerCommand(this, new BPingCommand());
        this.manager.registerCommand(this, new BSetPrefixCommand());
        this.manager.registerCommand(this, new BSurvivalCommand());
    }

    public void loadConfiguration() {
        this.config = new Config();
    }

    public void saveConfiguration() {
        this.config.save();
    }

    public void log(String text) {
        this.getProxy().getLogger().info(prefix + text);
    }

    public void warning(String text) {
        this.getProxy().getLogger().warning(prefix + text);
    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        MarioMain.prefix = prefix;
    }

    public Config getConfig() {
        return this.config;
    }

    public static MarioMain getInstance() {
        return marioMain;
    }
}
