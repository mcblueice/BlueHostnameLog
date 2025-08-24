package net.mcblueice.bluehostnamelog;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class BlueHostnameLog extends JavaPlugin implements Listener {

    private final Map<String, String> lastHostnames = new ConcurrentHashMap<>();
    private final Map<String, String> lastIps = new ConcurrentHashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info(ChatColor.GREEN + "登入紀錄插件已啟動!");

        // 註冊 PlaceholderAPI Expansion
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new BlueHostnameLogExpansion(this).register();
            getLogger().info(ChatColor.GREEN + "已註冊 PlaceholderAPI 擴充!");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.RED + "登入紀錄插件已關閉!");
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        String playerName = event.getPlayer().getName();
        String hostname = event.getHostname();
        String ip = event.getAddress().getHostAddress();
        lastHostnames.put(playerName, hostname);
        lastIps.put(playerName, ip);
        Bukkit.getConsoleSender().sendMessage(
            ChatColor.GRAY + "[" +
            ChatColor.AQUA + "玩家登入紀錄" +
            ChatColor.GRAY + "]" +
            ChatColor.YELLOW + "玩家 " +
            ChatColor.AQUA + playerName +
            ChatColor.YELLOW + " 從 " +
            ChatColor.GREEN + hostname +
            ChatColor.YELLOW + " (IP: " +
            ChatColor.BLUE + ip +
            ChatColor.YELLOW + ") 登入伺服器"
        );
    }

    public String getLastHostname(String playerName) {
        return lastHostnames.getOrDefault(playerName, "未知");
    }

    public String getLastIp(String playerName) {
        return lastIps.getOrDefault(playerName, "未知");
    }
}
