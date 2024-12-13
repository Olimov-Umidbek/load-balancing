package load.balancing.balancer.impl;

import load.balancing.conf.Properties;
import load.balancing.exception.ServerNotFoundException;
import load.balancing.model.domain.Server;
import load.balancing.servers.ServerManager;

import java.util.Comparator;

public class WeightedRobinRoundImpl extends BaseLoadBalancer implements LoadBalancer {

    public WeightedRobinRoundImpl(Properties properties, ServerManager serverManager) {
        super(properties, serverManager);
    }

    @Override
    public Server getServer() {
        return serverManager.getServerList()
            .stream()
            .min(Comparator.comparing(Server::getWeight))
            .orElseThrow(() -> new ServerNotFoundException("The server not found"));
    }
}
