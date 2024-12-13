package load.balancing.balancer.impl;

import load.balancing.model.domain.Server;

public interface LoadBalancer {

    Server getServer();
}
