package load.balancing.balancer;

import load.balancing.balancer.impl.LoadBalancer;
import load.balancing.balancer.impl.RobinRoundImpl;
import load.balancing.balancer.impl.WeightedRobinRoundImpl;
import load.balancing.conf.Properties;
import load.balancing.exception.LoadBalancerAlgorithmNotFoundException;
import load.balancing.model.enums.LoadBalancerAlgorithm;
import load.balancing.servers.ServerManager;

public class LoadBalancerFactory {
    private final Properties properties;
    private final ServerManager serverManager;

    public LoadBalancerFactory(Properties properties, ServerManager serverManager) {
        this.properties = properties;
        this.serverManager = serverManager;
    }

    public LoadBalancer getLoadBalancer() {
        switch (properties.algorithm()) {
            case ROBIN_ROUND -> {
                return new RobinRoundImpl(properties, serverManager);
            }
            case WEIGHTED_ROBIN_ROUND -> {
                return new WeightedRobinRoundImpl(properties, serverManager);
            }
            default -> throw new LoadBalancerAlgorithmNotFoundException("Load balancer implementation not found with " + properties.algorithm() + " algorithm.");
        }
    }
}
