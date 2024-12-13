package load.balancing.exception;

public class LoadBalancerAlgorithmNotFoundException extends LoadBalancerException{
    public LoadBalancerAlgorithmNotFoundException(String message) {
        super(message);
    }

    public LoadBalancerAlgorithmNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
