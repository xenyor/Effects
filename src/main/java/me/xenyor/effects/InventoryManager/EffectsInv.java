package me.xenyor.effects.InventoryManager;

import com.google.common.collect.Maps;
import me.xenyor.effects.Main.EffectsMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.*;

public class EffectsInv {

    public static String invName = ChatColor.translateAlternateColorCodes('&', EffectsMain.getCustomConfig().getString("inventory.inventory-name"));

    private static String InfoName = EffectsMain.getCustomConfig().getString("inventory.inventory-info-name");
    private static String InfoLore = EffectsMain.getCustomConfig().getString("inventory.inventory-info-lore");

    private static String SpeedName = EffectsMain.getCustomConfig().getString("effects.speed.inventory-speed-name");
    private static String SpeedLore = EffectsMain.getCustomConfig().getString("effects.speed.inventory-speed-lore");
    private static String JumpName = EffectsMain.getCustomConfig().getString("effects.jump.inventory-jump-name");
    private static String JumpLore = EffectsMain.getCustomConfig().getString("effects.jump.inventory-jump-lore");
    private static String InvisName = EffectsMain.getCustomConfig().getString("effects.invisible.inventory-invisible-name");
    private static String InvisLore = EffectsMain.getCustomConfig().getString("effects.invisible.inventory-invisible-lore");
    private static String RegenName = EffectsMain.getCustomConfig().getString("effects.regeneration.inventory-regeneration-name");
    private static String RegenLore = EffectsMain.getCustomConfig().getString("effects.regeneration.inventory-regeneration-lore");

    public static Integer SpeedDuration = EffectsMain.getCustomConfig().getInt("effects.speed.effect-speed-duration");
    public static Integer SpeedAmp = EffectsMain.getCustomConfig().getInt("effects.speed.effect-speed-amplifier");
    public static Integer JumpDuration = EffectsMain.getCustomConfig().getInt("effects.jump.effect-jump-duration");
    public static Integer JumpAmp = EffectsMain.getCustomConfig().getInt("effects.jump.effect-jump-amplifier");
    public static Integer InvisDuration = EffectsMain.getCustomConfig().getInt("effects.invisible.effect-invisible-duration");
    public static Integer RegenDuration = EffectsMain.getCustomConfig().getInt("effects.regeneration.effect-regeneration-duration");
    public static Integer RegenAmp = EffectsMain.getCustomConfig().getInt("effects.regeneration.effect-regeneration-amplifier");


    public static Long SpeedCd = EffectsMain.getCustomConfig().getLong("effects.speed.effect-speed-cooldown");
    public static Long JumpCd = EffectsMain.getCustomConfig().getLong("effects.jump.effect-jump-cooldown");
    public static Long InvisCd = EffectsMain.getCustomConfig().getLong("effects.invisible.effect-invisible-cooldown");
    public static Long RegenCd = EffectsMain.getCustomConfig().getLong("effects.regeneration.effect-regeneration-cooldown");


    public static String noperms = EffectsMain.getCustomConfig().getString("plugin-no-permission");
    //private static String onCooldown = EffectsMain.getCustomConfig().getString("plugin-effect-on-cooldown");

    public static Map<UUID, Long> speedCooldown = Maps.newHashMap();
    public static Map<UUID, Long> jumpCooldown = Maps.newHashMap();
    public static Map<UUID, Long> invisCooldown = Maps.newHashMap();
    public static Map<UUID, Long> regenCooldown = Maps.newHashMap();

    private static List<ItemStack> effectInvItems = new ArrayList<>();

    public static void openInventory(Player player) {

        if(!speedCooldown.containsKey(player.getUniqueId()) || !jumpCooldown.containsKey(player.getUniqueId()) || !invisCooldown.containsKey(player.getUniqueId()) || !regenCooldown.containsKey(player.getUniqueId())) {
            speedCooldown.put(player.getUniqueId(), (System.currentTimeMillis() / 1000) + SpeedCd);
            jumpCooldown.put(player.getUniqueId(), (System.currentTimeMillis() / 1000) + JumpCd);
            invisCooldown.put(player.getUniqueId(), (System.currentTimeMillis() / 1000) + InvisCd);
            regenCooldown.put(player.getUniqueId(), (System.currentTimeMillis() / 1000) + RegenCd);
        }

            Inventory inv = Bukkit.createInventory(null, 9 * 3, ChatColor.translateAlternateColorCodes('&', invName));
            inv.clear();

            updateInventory(inv);

            player.openInventory(inv);

    }

    private static void createItems() {

        EffectsInv ei = new EffectsInv();

        // Speed Potion
        ei.newPotionItem(Material.POTION, PotionType.SPEED, SpeedName, SpeedLore);

        // Jump Potion
        ei.newPotionItem(Material.POTION, PotionType.JUMP, JumpName, JumpLore);

        // Invis Potion
        ei.newPotionItem(Material.POTION, PotionType.INVISIBILITY, InvisName, InvisLore);

        // Regen Potion
        ei.newPotionItem(Material.POTION, PotionType.REGEN, RegenName, RegenLore);

    }

    public void newPotionItem(Material material, PotionType potionType, String displayName, String lore) {

        // Create new item
        ItemStack itemMaterial = new ItemStack(material);
        PotionMeta itemMeta = (PotionMeta) itemMaterial.getItemMeta();
        itemMeta.setBasePotionData(new PotionData(potionType));
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        itemMeta.setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', (lore))));
        itemMaterial.setItemMeta(itemMeta);

        // Add item to list, this is used to put all items into inv
        effectInvItems.add(itemMaterial);
    }


    private static void updateInventory(Inventory inv) {

        inv.clear();


        // Manually setting "Info" item
        ItemStack Info = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta InfoMeta = Info.getItemMeta();
        InfoMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', InfoName));
        InfoMeta.setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', InfoLore)));
        Info.setItemMeta(InfoMeta);

        inv.setItem(4, Info);

        createItems();

        // Putting every "Potion" item into the inventory
        for(int i = 10; i<=13; i++) {
            inv.setItem(i, effectInvItems.get(i-10));
        }



    }

}
