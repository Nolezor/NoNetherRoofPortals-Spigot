package me.nolezor.nonetherroofportals.events;

import me.nolezor.nonetherroofportals.NoNetherRoofPortals;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class DestroyPortalEvent implements Listener {

    @EventHandler
    public void onPlayerEnterPortal(PlayerPortalEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("nonetherroofportals.bypass") && !(p.isOp() && NoNetherRoofPortals.opBypass) && NoNetherRoofPortals.destroyPortal) {
            if (p.getWorld().getEnvironment().equals(World.Environment.NETHER) && p.getLocation().getY() >= 127) {
                Block block = p.getLocation().getBlock();
                if (block.getType() == Material.NETHER_PORTAL) {
                    block.breakNaturally();
                    e.setCancelled(true);
                }
            }
        }
    }
}
