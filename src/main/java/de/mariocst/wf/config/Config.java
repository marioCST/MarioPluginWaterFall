package de.mariocst.wf.config;

import de.mariocst.wf.MarioMain;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    private Configuration configuration;
    private static Config config;
    private final File file;
    private String prefix;
    private String lobbyServer;
    private String survivalServer;

    public Config() {
        config = this;

        File dir = MarioMain.getInstance().getDataFolder();

        dir.mkdirs();

        file = new File(dir, "config.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(dir, "config.yml"));
        } catch (IOException ignored) { }

        this.registerContent();
    }

    public void registerContent() {
        if (this.configuration.contains("prefix")) {
            this.prefix = this.configuration.getString("prefix");
        }
        else {
            this.prefix = "§8[§6marioCST.de§8] §f";
        }

        if (this.configuration.contains("lobbyServer")) {
            this.lobbyServer = this.configuration.getString("lobbyServer");
        }
        else {
            this.lobbyServer = "lobby";
        }

        if (this.configuration.contains("survivalServer")) {
            this.survivalServer = this.configuration.getString("survivalServer");
        }
        else {
            this.survivalServer = "survival";
        }
    }

    public void save() {
        File dir = MarioMain.getInstance().getDataFolder();

        this.configuration.set("prefix", this.prefix);
        this.configuration.set("lobbyServer", this.lobbyServer);
        this.configuration.set("survivalServer", this.survivalServer);

        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.configuration, new File(dir, "config.yml"));
        } catch (IOException ignored) { }
    }

    public static Config getConfig() {
        return config;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public File getFile() {
        return this.file;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getLobbyServer() {
        return lobbyServer;
    }

    public String getSurvivalServer() {
        return survivalServer;
    }
}
