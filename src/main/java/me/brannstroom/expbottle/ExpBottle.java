package me.brannstroom.expbottle;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

import me.brannstroom.expbottle.command.ExpBottleCommand;
import me.brannstroom.expbottle.listeners.ExpBottleListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Lists;

public class ExpBottle extends JavaPlugin {

    public static ExpBottle instance;

    public void onEnable() {
        instance = this;
        loadListeners();
        loadCommands();

        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }

    public void onDisable() {

    }

    private void loadListeners() {
        getServer().getPluginManager().registerEvents(new ExpBottleListener(), this);
    }

    private void loadCommands() {


        List<String> aliasList = ExpBottle.getPlugin(ExpBottle.class).getConfig().getStringList("commandAliases");
        String[] aliases = new String[aliasList.size()];
        aliasList.toArray(aliases);

        registerCommand("xpbottle", new ExpBottleCommand(), aliases);

    }

    public void registerCommand(String name, CommandExecutor executor, String... aliases) {
        try {
            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);

            PluginCommand command = constructor.newInstance(name, this);

            command.setExecutor(executor);
            command.setAliases(Lists.newArrayList(aliases));
            if (executor instanceof TabCompleter) {
                command.setTabCompleter((TabCompleter) executor);
            }
            this.getCommandMap().register("expbottle", command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CommandMap getCommandMap() {
        try {
            org.bukkit.Server server = Bukkit.getServer();
            Field commandMap = server.getClass().getDeclaredField("commandMap");
            commandMap.setAccessible(true);
            return (CommandMap) commandMap.get(server);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }
}