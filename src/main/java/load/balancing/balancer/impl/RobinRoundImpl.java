package load.balancing.balancer.impl;

import load.balancing.conf.Properties;
import load.balancing.model.domain.Server;
import load.balancing.servers.ServerManager;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RobinRoundImpl extends BaseLoadBalancer implements LoadBalancer {
    private final AtomicInteger atomicInteger = new AtomicInteger();

    public RobinRoundImpl(Properties properties, ServerManager serverManager) {
        super(properties, serverManager);
    }

    @Override
    public Server getServer() {
        int inc = atomicInteger.incrementAndGet();
        List<Server> serverList = serverManager.getServerList();
        return serverList.get(inc % serverList.size() - 1);
    }
}
