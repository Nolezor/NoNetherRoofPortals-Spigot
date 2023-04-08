package me.nolezor.nonetherroofportals;

import me.nolezor.nonetherroofportals.events.DestroyPortalEvent;
import me.nolezor.nonetherroofportals.events.JoinRoofTeleportEvent;
import me.nolezor.nonetherroofportals.events.ObsidianPlaceEvent;
import me.nolezor.nonetherroofportals.events.PortalLitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoNetherRoofPortals extends JavaPlugin {
    private static NoNetherRoofPortals plugin;

    public static boolean opBypass;
    public static boolean obsidianBlock;
    public static boolean litBlock;
    public static boolean destroyPortal;
    public static boolean teleportOnJoin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        plugin = this;
        opBypass = getConfig().getBoolean("OpCanBypass");
        obsidianBlock = getConfig().getBoolean("Obsidian");
        litBlock = getConfig().getBoolean("Flint&Steel");
        destroyPortal = getConfig().getBoolean("DestroyExistingPortals");
        teleportOnJoin = getConfig().getBoolean("ForceTeleportDown");

        getServer().getPluginManager().registerEvents(new ObsidianPlaceEvent(), this);
        getServer().getPluginManager().registerEvents(new PortalLitEvent(), this);
        getServer().getPluginManager().registerEvents(new DestroyPortalEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinRoofTeleportEvent(), this);
    }

    public static NoNetherRoofPortals getPlugin() {
        return plugin;
    }
}
