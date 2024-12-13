package load.balancing.balancer.impl;

import load.balancing.conf.Properties;
import load.balancing.model.domain.Server;
import load.balancing.servers.ServerManager;

public abstract class BaseLoadBalancer {

    protected final Properties properties;
    protected final ServerManager serverManager;

    protected BaseLoadBalancer(Properties properties, ServerManager serverManager) {
        this.properties = properties;
        this.serverManager = serverManager;
    }

    protected abstract Server getServer();
}
