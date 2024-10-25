package be.kingquest.friendSpigot.manager;

import be.kingquest.friendSpigot.FriendSpigot;
import be.kingquest.friendSpigot.redis.RedisClient;
import be.kingquest.friendSpigot.synchronizer.SpigotSynchronizer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Random;


public class RedisManager {

    private final FriendSpigot friendSpigot;

    private final File file;
    private final YamlConfiguration cfg;

    private RedisClient redisClient;
    private String serverName;

    public RedisManager(FriendSpigot friendSpigot) {
        this.friendSpigot = friendSpigot;
        this.file = new File(friendSpigot.getDataFolder(), "redis.yml");
        this.cfg = YamlConfiguration.loadConfiguration(file);
        createFile();
        readFile();
    }

    public void createFile() {
        if (!file.exists()) {
            cfg.set("host", "127.0.0.1");
            cfg.set("port", 6379);
            cfg.set("user", "user");
            cfg.set("password", "password");
            cfg.set("clientName", "clientName");
            cfg.set("database", 0);
            cfg.set("keyPrefix", "hitboxutils.");
            cfg.set("messageChannel", "hitboxutils");
            cfg.set("messageSalt", getRandomString(20));
            cfg.set("serverName", "proxy1");
            try {
                cfg.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void readFile() {
        if (this.redisClient != null) {
            this.redisClient.close();
        }

        this.serverName = cfg.getString("serverName", "server1");


        this.redisClient = RedisClient.builder()
                .host(cfg.getString("host", "127.0.0.1"))
                .port(cfg.getInt("port", 6379))
                .user(getNullOrString(cfg.getString("user")))
                .password(getNullOrString(cfg.getString("password")))
                .clientName(getNullOrString(cfg.getString("clientName")))
                .database(cfg.getInt("database", 0))
                .keyPrefix(cfg.getString("keyPrefix", "kingquest_friend."))
                .messageChannel(cfg.getString("messageChannel", "kingquest_friend"))
                .messageSalt(cfg.getString("messageSalt", "default-message-salt"))
                .logger(friendSpigot.getLogger())
                .synchronizer(new SpigotSynchronizer(friendSpigot))
                .build();

        friendSpigot.runAsync(() -> redisClient.getRedisCache().connect());
        friendSpigot.runAsync(() -> redisClient.getRedisPublisher().connect());
        friendSpigot.runAsync(() -> redisClient.getRedisSubscriber().connect());

    }

    private String getNullOrString(String s) {
        if ("null".equals(s)) return null;
        return s;
    }

    private String getRandomString(int length) {
        final char[] characters = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        final StringBuilder sb = new StringBuilder();
        final Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(characters[random.nextInt(characters.length)]);
        }
        return sb.toString();
    }

    public void runAfterReady(Runnable run) {
        redisClient.runAfterReady(run);
    }
}
