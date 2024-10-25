package be.kingquest.friendSpigot;

import be.kingquest.friendSpigot.gui.Freunde;
import be.kingquest.friendSpigot.manager.RedisManager;
import org.bukkit.plugin.messaging.PluginMessageListener;
import be.kingquest.friendSpigot.listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.List;

public final class FriendSpigot extends JavaPlugin implements PluginMessageListener {

    private RedisManager redisManager;

    @Override
    public void onEnable() {

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);

        // Registriere den Plugin-Kanal für eingehende Nachrichten
        getServer().getMessenger().registerIncomingPluginChannel(this, "friend", this);

        // Registriere den Plugin-Kanal auch als ausgehenden Kanal
        getServer().getMessenger().registerOutgoingPluginChannel(this, "friend");

        redisManager = new RedisManager(this);


    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        player.sendMessage("E1");
        if (channel.equals("friend")) {
            player.sendMessage("E2");
            String receivedData = new String(message);
            List<String> onlineUsernames = new ArrayList<>();

            // Zerlege die empfangenen Daten in Benutzernamen
            String[] usernames = receivedData.split(",");
            for (String username : usernames) {
                if (!username.isEmpty()) {
                    onlineUsernames.add(username);
                }
            }
            player.sendMessage("E1");

            // Öffne das Menü mit den online Benutzernamen
            new Freunde(player, onlineUsernames);
        }
    }

    public void runAsync(Runnable runnable) {
        getServer().getScheduler().runTaskAsynchronously(this, runnable);
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }
}
