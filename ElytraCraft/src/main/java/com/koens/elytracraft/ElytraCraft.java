package com.koens.elytracraft;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class ElytraCraft extends JavaPlugin implements Listener{
    @Override
    public void onEnable() {
        getLogger().info("Elytra enabled!");
        ItemStack i = null;
        ShapedRecipe elytraRecipe = new ShapedRecipe(new ItemStack(Material.ELYTRA, 1))
                .shape("sls", "lel","ldl")
                .setIngredient('l', Material.LEATHER)
                .setIngredient('s', Material.STRING)
                .setIngredient('e', Material.EYE_OF_ENDER)
                .setIngredient('d', Material.DIAMOND);
        getServer().addRecipe(elytraRecipe);
        getServer().getPluginManager().registerEvents(this, this);
    }
    @Override
    public void onDisable() {
        getLogger().info("Elytra disabled!");
    }
    @EventHandler
    public void onCraft(CraftItemEvent event) {
        HumanEntity p = event.getWhoClicked();
        if (p instanceof Player) {
            Player pp = (Player) event.getWhoClicked();
            if(!pp.hasPermission("elytracraft.craft")){
                event.setCancelled(true);
                ItemStack[] used = event.getInventory().getContents();
                event.getInventory().clear();
                for (ItemStack u : used) {
                    pp.getInventory().addItem(u);
                }
                pp.closeInventory();
                pp.sendMessage(ChatColor.RED + "You are not allowed to craft an elytra because of permission elytracraft.craft!");
            }
        }
    }
}
