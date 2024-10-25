package be.kingquest.friendSpigot.redis.handler;

@Data
@AllArgsConstructor
public abstract class CustomMessageHandler {

    private final String identifier;

    public abstract void processMessage(String message);

}