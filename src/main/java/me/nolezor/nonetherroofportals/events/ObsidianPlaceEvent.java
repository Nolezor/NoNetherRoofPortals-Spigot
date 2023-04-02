package me.nolezor.nonetherroofportals.events;

import me.nolezor.nonetherroofportals.NoNetherRoofPortals;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class ObsidianPlaceEvent implements Listener {

    @EventHandler
    public void onPlayerObsidianPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("nonetherroofportals.bypass") && !(p.isOp() && NoNetherRoofPortals.opBypass)  && NoNetherRoofPortals.obsidianBlock) {
            if (e.getBlockPlaced().getType() == Material.OBSIDIAN && e.getBlockPlaced().getWorld().getEnvironment() == World.Environment.NETHER && e.getBlockPlaced().getLocation().getY() >= 127 && NoNetherRoofPortals.obsidianBlock) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "You can't place obsidian here.");
            }
        }
    }
}
