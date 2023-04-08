package me.nolezor.nonetherroofportals.events;

import me.nolezor.nonetherroofportals.NoNetherRoofPortals;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinRoofTeleportEvent implements Listener {

    @EventHandler
    public void onPlayerJoinNetherRoof(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.getWorld().getEnvironment().equals(World.Environment.NETHER) && p.getLocation().getY() >= 126) {
            if (!p.hasPermission("nonetherroofportals.bypass") && !(p.isOp() && NoNetherRoofPortals.opBypass) && NoNetherRoofPortals.teleportOnJoin) {
                Location loc = searchSafeLocation(p.getLocation());
                if (loc == null) {
                    System.out.println("Couldn't find a safe location.");
                } else {
                    p.teleport(loc);
                    loc.setX(loc.getX() + 2);
                    generatePortal(loc);
                }
            }
        }
    }

    public static Location searchSafeLocation(Location loc) {
        Chunk chunk = loc.getWorld().getChunkAt(loc);
        Location safeLocation;
        int chunkX = chunk.getX()*16, chunkZ = chunk.getZ()*16;
        for (int i = 0; i < 9; i++) {
            for (int x = chunkX; x <= chunkX + 15; x++) {
                loc.setX(x-.5);
                for (int z = chunkZ; z <= chunkZ + 15; z++) {
                    loc.setZ(z-.5);
                    safeLocation = heightSearch(loc);
                    if (safeLocation != null) {
                        return safeLocation;
                    }
                }
            }
            chunkX+=16;
        }
        return null;
    }

    private static Location heightSearch(Location loc) {
        for (int y = 32; y < 127; y++) {
            loc.setY(y);
            Block footBlock = loc.getBlock();
            loc.setY(y+1);
            Block mainBlock = loc.getBlock();
            loc.setY(y-1);
            Block belowBlock = loc.getBlock();
            if (footBlock.getType() == Material.AIR && mainBlock.getType() == Material.AIR && !belowBlock.isPassable()) {
                loc.setY(y);
                return loc;
            }
        }
        return null;
    }

    public static void generatePortal(Location loc) {
        int locZ = (int)loc.getZ(), locY = (int)loc.getY();
        for (int z = locZ; z < locZ + 4; z++) {
            loc.setZ(z-.5);
            for (int y = locY; y < locY + 5; y++) {
                loc.setY(y);
                if ((z == locZ+1 || z == locZ+2) && (y == locY+1 || y == locY+2 || y == locY+3)) {
                    loc.getBlock().setType(Material.AIR);
                } else {
                    loc.getBlock().setType(Material.OBSIDIAN);
                }
            }
        }
        loc.setZ(locZ+1);
        loc.setY(locY+1);
        loc.getBlock().setType(Material.FIRE);
    }
}
