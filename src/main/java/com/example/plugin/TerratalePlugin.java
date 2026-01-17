package com.example.plugin;

import com.example.commands.HelpCommand;
import com.example.commands.TestCommand;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import java.util.logging.Level;

public class TerratalePlugin extends JavaPlugin {

    private static TerratalePlugin instance;

    public TerratalePlugin(@NonNullDecl JavaPluginInit init) {
        super(init);
    }

    public static TerratalePlugin get() {
        return instance;
    }

    @Override
    protected void setup() {
        instance = this;

        PluginFolders.setup(this);

        getCommandRegistry().registerCommand(new HelpCommand());
        getCommandRegistry().registerCommand(new TestCommand());
        getLogger().at(Level.INFO).log("Plugin setup complete!");
    }

    @Override
    protected void start() {
        getLogger().at(Level.INFO).log("Plugin started!");
    }

    @Override
    protected void shutdown() {
        getLogger().at(Level.INFO).log("Plugin shutting down!");
    }
}
