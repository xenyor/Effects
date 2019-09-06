package me.xenyor.effects.Main.InventoryManager;

import me.xenyor.effects.Main.Main.EffectsMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EffectsOpenInv implements CommandExecutor {
    private static final String noperms = EffectsMain.getCustomConfig().getString("plugin-no-permission");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] strings) {
        if (cmd.getName().equalsIgnoreCase("effects")) {
            Player player = (Player) sender;
            if (player.hasPermission("effects.menu") || player.hasPermission("effects.*")) {

                EffectsInv.openInventory(player);

            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', noperms));
            }
            return true;
        }
        return true;
    }
}
