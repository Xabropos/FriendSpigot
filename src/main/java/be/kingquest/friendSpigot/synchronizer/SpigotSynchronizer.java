package be.kingquest.friendSpigot.synchronizer;

import be.kingquest.friendSpigot.FriendSpigot;
import be.kingquest.friendSpigot.redis.Synchronizer;
import be.kingquest.friendSpigot.redis.packet.test.TestPacket;

public class SpigotSynchronizer implements Synchronizer {

    private final FriendSpigot fiFriendSpigot;

    public SpigotSynchronizer(FriendSpigot fiFriendSpigot) {
        this.fiFriendSpigot = fiFriendSpigot;
    }

    @Override
    public void handlePacket(TestPacket teamChatPacket) {
        System.out.println("Received packet: " + teamChatPacket.getMsg());
    }
}
