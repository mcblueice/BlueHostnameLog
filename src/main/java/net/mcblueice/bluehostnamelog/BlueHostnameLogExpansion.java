package net.mcblueice.bluehostnamelog;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BlueHostnameLogExpansion extends PlaceholderExpansion {

    private final BlueHostnameLog plugin;

    public BlueHostnameLogExpansion(BlueHostnameLog plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "bluehostnamelog";
    }

    @Override
    public @NotNull String getAuthor() {
        return "mcblueice";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) return "";
        if (identifier.equalsIgnoreCase("hostname")) {
            return plugin.getLastHostname(player.getName());
        }
        if (identifier.equalsIgnoreCase("ip")) {
            return plugin.getLastIp(player.getName());
        }
        return null;
    }
}