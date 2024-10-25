package be.kingquest.friendSpigot.redis.packet.test;

import be.kingquest.friendSpigot.redis.packet.FriendPacket;
import lombok.Getter;

@Getter
public class TestPacket extends FriendPacket {

    private final String msg;

    public TestPacket(String source, String msg) {
        super(source);
        this.msg = msg;
    }
}
