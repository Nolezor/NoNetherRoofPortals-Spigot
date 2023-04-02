package me.nolezor.nonetherroofportals.events;

import me.nolezor.nonetherroofportals.NoNetherRoofPortals;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PortalLitEvent implements Listener {

    @EventHandler
    public void onPlayerPortalListTesti(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("nonetherroofportals.bypass") && !(p.isOp() && NoNetherRoofPortals.opBypass) && NoNetherRoofPortals.litBlock) {
            if (p.getInventory().getItemInMainHand().getType() == Material.FLINT_AND_STEEL && p.getInventory().getItemInMainHand().getType() == Material.FLINT_AND_STEEL) {
                if (e.getClickedBlock() != null) {
                    if (e.getClickedBlock().getType() == Material.OBSIDIAN && e.getClickedBlock().getWorld().getEnvironment().equals(World.Environment.NETHER) && e.getClickedBlock().getLocation().getY() >= 127) {
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED + "You can't lit portals here.");
                    }
                }
            }
        }
    }

}
