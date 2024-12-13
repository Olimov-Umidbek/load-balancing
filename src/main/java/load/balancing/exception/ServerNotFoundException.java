package load.balancing.exception;

public class ServerNotFoundException extends LoadBalancerException{
    public ServerNotFoundException(String message) {
        super(message);
    }

    public ServerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
