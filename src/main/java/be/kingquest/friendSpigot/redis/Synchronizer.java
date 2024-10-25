package be.kingquest.friendSpigot.redis;


import be.kingquest.friendSpigot.redis.packet.FriendPacket;
import be.kingquest.friendSpigot.redis.packet.test.TestPacket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface Synchronizer {

    default void packedReceived(FriendPacket packet) {
        ignoreException(() -> preHandlePacket(packet));
        try {
            final Method method = getClass().getMethod("handlePacket", packet.getClass());
            method.invoke(this, packet);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            handleCustomPacket(packet);
        }
        ignoreException(() -> postHandlePacket(packet));
    }
    default void preHandlePacket(FriendPacket packet) {}

    default void postHandlePacket(FriendPacket packet) {}
    default void handleCustomPacket(FriendPacket packet) {}

    private void ignoreException(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable ignored) {}
    }

    default void handlePacket(TestPacket teamChatPacket) {}

}
