package load.balancing;

import load.balancing.balancer.LoadBalancerFactory;
import load.balancing.balancer.impl.LoadBalancer;
import load.balancing.conf.Properties;
import load.balancing.model.enums.LoadBalancerAlgorithm;
import load.balancing.servers.ServerManagersFactory;

public class Main {

    public static void main(String[] args) {
        Properties properties = new Properties(10);
        ServerManagersFactory serverManagersFactory = new ServerManagersFactory(properties);
        LoadBalancerFactory loadBalancerFactory = new LoadBalancerFactory(properties, serverManagersFactory);
        loadBalancerFactory.getLoadBalancer(LoadBalancerAlgorithm.ROBIN_ROUND);

    }
}
