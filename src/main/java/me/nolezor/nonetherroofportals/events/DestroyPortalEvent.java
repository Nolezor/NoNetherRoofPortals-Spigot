package me.nolezor.nonetherroofportals.events;

import me.nolezor.nonetherroofportals.NoNetherRoofPortals;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class DestroyPortalEvent implements Listener {

    @EventHandler
    public void onPlayerEnterPortal(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        if (e.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
            if (!p.hasPermission("nonetherroofportals.bypass") && !(p.isOp() && NoNetherRoofPortals.opBypass) && NoNetherRoofPortals.destroyPortal) {
                if (p.getWorld().getEnvironment().equals(World.Environment.NETHER) && p.getLocation().getY() >= 127) {
                    Block block = p.getLocation().getBlock();
                    if (block.getType() == Material.NETHER_PORTAL) {
                        block.breakNaturally();
                        e.setCancelled(true);
                    }
                } else if (p.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
                    Location loc = e.getTo();
                    if (loc.getY() >= 127) {
                        if (loc.getBlock().getType() == Material.NETHER_PORTAL) {
                            loc.getBlock().breakNaturally();
                            Location safeLocation = JoinRoofTeleportEvent.searchSafeLocation(loc);
                            p.teleport(safeLocation);
                            safeLocation.setY(loc.getY()-1);
                            JoinRoofTeleportEvent.generatePortal(safeLocation);
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

}
