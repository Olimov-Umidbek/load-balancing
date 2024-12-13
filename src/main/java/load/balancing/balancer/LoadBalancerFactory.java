package load.balancing.balancer;

import load.balancing.balancer.impl.BaseLoadBalancer;
import load.balancing.balancer.impl.RobinRoundImpl;
import load.balancing.balancer.impl.WeightedRobinRoundImpl;
import load.balancing.conf.Properties;
import load.balancing.exception.LoadBalancerAlgorithmNotFoundException;
import load.balancing.model.enums.LoadBalancerAlgorithm;
import load.balancing.servers.ServerManagersFactory;

public class LoadBalancerFactory {
    private final Properties properties;
    private final ServerManagersFactory serverManagersFactory;

    public LoadBalancerFactory(Properties properties, ServerManagersFactory serverManagersFactory) {
        this.properties = properties;
        this.serverManagersFactory = serverManagersFactory;
    }

    public BaseLoadBalancer getLoadBalancer(LoadBalancerAlgorithm algorithm) {
        switch (algorithm) {
            case ROBIN_ROUND -> {
                return new RobinRoundImpl(properties, serverManagersFactory.getServerManager());
            }
            case WEIGHTED_ROBIN_ROUND -> {
                return new WeightedRobinRoundImpl(properties, serverManagersFactory.getServerManager());
            }
            default -> throw new LoadBalancerAlgorithmNotFoundException("Load balancer implementation not found with " + algorithm + " algorithm.");
        }
    }
}
