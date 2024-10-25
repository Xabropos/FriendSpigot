package be.kingquest.friendSpigot.redis.handler;


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
        final HitBoxPacket packet = container.getPacket();
        synchronizer.packedReceived(packet);
    }
}
