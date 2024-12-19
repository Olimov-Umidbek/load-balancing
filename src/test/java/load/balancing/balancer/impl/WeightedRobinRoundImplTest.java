package load.balancing.balancer.impl;

import load.balancing.balancer.LoadBalancerFactory;
import load.balancing.conf.Properties;
import load.balancing.model.domain.Server;
import load.balancing.model.enums.LoadBalancerAlgorithm;
import load.balancing.servers.ServerManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeightedRobinRoundImplTest {
    private final Properties properties = mock(Properties.class);
    private final ServerManager serverManager = mock(ServerManager.class);
    private LoadBalancer loadBalancer;
    private final List<Server> serverList = new CopyOnWriteArrayList<>();

    @Before
    public void before() {
        when(properties.algorithm()).thenReturn(LoadBalancerAlgorithm.WEIGHTED_ROBIN_ROUND);

        loadBalancer = new LoadBalancerFactory(properties, serverManager).getLoadBalancer();
        IntStream.range(1, 10).forEach(it ->
            serverList.add(
                new Server(it, "address-" + it, it * 1./ 10, it)
            )
        );
    }

    @Test
    public void getServer() {
        when(serverManager.getServerList()).thenReturn(serverList);
        IntStream.range(1, 10).forEach(it -> {
                Assert.assertEquals(serverList.get(it - 1), loadBalancer.getServer());
                serverList.get(it - 1).setWeight((10 + it) * 1./ 10);
            }
        );
    }
}