package com.bnfsmp.deathitemdespawn;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class DeathItemDespawn extends JavaPlugin implements Listener {

    private int despawnTicks;

    // Temporarily store death drops
    private final List<ItemStack> pendingDrops = new ArrayList<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        int minutes = getConfig().getInt("despawn-time-minutes", 30);
        despawnTicks = minutes * 60 * 20;

        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("DeathItemDespawn enabled (" + minutes + " minutes).");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        pendingDrops.clear();
        pendingDrops.addAll(event.getDrops());

        // Clear after short delay so it only catches THIS death
        Bukkit.getScheduler().runTaskLater(this, pendingDrops::clear, 20L);
    }

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {

        Item item = event.getEntity();
        ItemStack stack = item.getItemStack();

        for (ItemStack drop : pendingDrops) {

            if (stack.isSimilar(drop)) {

                // Mark item
                item.setMetadata("deathItem", new FixedMetadataValue(this, true));

                // Prevent vanilla despawn
                item.setUnlimitedLifetime(true);

                // Schedule removal
                Bukkit.getScheduler().runTaskLater(this, () -> {
                    if (item.isValid() && item.hasMetadata("deathItem")) {
                        item.remove();
                    }
                }, despawnTicks);

                break;
            }
        }
    }
}