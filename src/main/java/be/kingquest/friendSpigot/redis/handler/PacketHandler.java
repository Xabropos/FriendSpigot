package be.kingquest.friendSpigot.redis.handler;

import be.kingquest.friendSpigot.redis.Synchronizer;
import be.kingquest.friendSpigot.redis.packet.FriendPacket;
import be.kingquest.friendSpigot.redis.packet.PacketContainer;
import lombok.Getter;

import static be.kingquest.friendSpigot.redis.RedisClient.RedisKeys.PACKET_PREFIX;


@Getter
public class PacketHandler extends CustomMessageHandler {

    private final Synchronizer synchronizer;

    public PacketHandler(Synchronizer synchronizer) {
        super(PACKET_PREFIX);
        this.synchronizer = synchronizer;
    }

    @Override
    public void processMessage(String message) {
        final PacketContainer container = PacketContainer.fromMessage(message);
        final FriendPacket packet = container.getPacket();
        synchronizer.packedReceived(packet);
    }
}
