package load.balancing.exception;

public class ServerListOutboundException extends LoadBalancerException{
    public ServerListOutboundException(String message) {
        super(message);
    }

    public ServerListOutboundException(String message, Throwable cause) {
        super(message, cause);
    }
}
