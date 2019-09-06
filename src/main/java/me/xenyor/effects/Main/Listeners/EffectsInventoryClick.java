package me.xenyor.effects.Main.Listeners;

import me.xenyor.effects.Main.InventoryManager.EffectsInv;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EffectsInventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory() == null) return;
        if (event.getCurrentItem() == null) return;

        Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', EffectsInv.invName))) {
            if (player.hasPermission("effects.menu") || player.hasPermission("effects.*")) {
                event.setCancelled(true);

                switch (event.getSlot()) {

                    case 10:
                        if ((System.currentTimeMillis() / 1000) >= (EffectsInv.speedCooldown.get(player.getUniqueId()) + EffectsInv.SpeedCd)) {
                            Bukkit.broadcastMessage("speed ingen cooldown, spelare får effect");

                            player.playSound(player.getLocation(), Sound.BLOCK_CLOTH_STEP, 1, 1);

                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * EffectsInv.SpeedDuration, EffectsInv.SpeedAmp));
                            player.closeInventory();

                            EffectsInv.speedCooldown.put(player.getUniqueId(), System.currentTimeMillis() / 1000);

                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis effect is on cooldown. Wait &l" + Long.toString((EffectsInv.speedCooldown.get(player.getUniqueId()) + EffectsInv.SpeedCd) - (System.currentTimeMillis() / 1000))) + " &cmore seconds!");
                        }
                        break;

                    case 11:
                        if ((System.currentTimeMillis() / 1000) >= (EffectsInv.jumpCooldown.get(player.getUniqueId()) + EffectsInv.JumpCd)) {

                            player.playSound(player.getLocation(), Sound.BLOCK_SLIME_PLACE, 1, 1);

                            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * EffectsInv.JumpDuration, EffectsInv.JumpAmp));
                            player.closeInventory();

                            EffectsInv.jumpCooldown.put(player.getUniqueId(), System.currentTimeMillis() / 1000);

                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis effect is on cooldown. Wait &l" + Long.toString((EffectsInv.jumpCooldown.get(player.getUniqueId()) + EffectsInv.JumpCd) - (System.currentTimeMillis() / 1000))) + " &cmore seconds!");
                        }
                        break;
                    case 12:
                        if ((System.currentTimeMillis() / 1000) >= (EffectsInv.invisCooldown.get(player.getUniqueId()) + EffectsInv.InvisCd)) {

                            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1);

                            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * EffectsInv.InvisDuration, 0));
                            player.closeInventory();

                            EffectsInv.invisCooldown.put(player.getUniqueId(), System.currentTimeMillis() / 1000);

                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis effect is on cooldown. Wait &l" + Long.toString((EffectsInv.invisCooldown.get(player.getUniqueId()) + EffectsInv.InvisCd) - (System.currentTimeMillis() / 1000))) + " &cmore seconds!");
                        }
                        break;
                    case 13:
                        if ((System.currentTimeMillis() / 1000) >= (EffectsInv.regenCooldown.get(player.getUniqueId()) + EffectsInv.RegenCd)) {

                            player.playSound(player.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1, 1);

                            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * EffectsInv.RegenDuration, EffectsInv.RegenAmp));
                            player.closeInventory();

                            EffectsInv.regenCooldown.put(player.getUniqueId(), System.currentTimeMillis() / 1000);

                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis effect is on cooldown. Wait &l" + Long.toString((EffectsInv.regenCooldown.get(player.getUniqueId()) + EffectsInv.RegenCd) - (System.currentTimeMillis() / 1000))) + " &cmore seconds!");
                        }
                        break;

                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', EffectsInv.noperms));
            }
        }
    }
}
