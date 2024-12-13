package load.balancing;

import load.balancing.balancer.LoadBalancerFactory;
import load.balancing.balancer.impl.LoadBalancer;
import load.balancing.conf.Properties;
import load.balancing.model.domain.Server;
import load.balancing.model.enums.LoadBalancerAlgorithm;
import load.balancing.servers.ServerManager;
import load.balancing.servers.ServerManagersFactory;

import java.util.logging.Logger;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("main");
        Properties properties = new Properties(10, LoadBalancerAlgorithm.WEIGHTED_ROBIN_ROUND);
        ServerManagersFactory serverManagersFactory = new ServerManagersFactory(properties);
        ServerManager serverManager = serverManagersFactory.getServerManager();
        IntStream.range(1,11).forEach( it ->
            serverManager.add(new Server(it, "http://localhost:808" + it, Math.sqrt(it), it  * 10))
        );

        LoadBalancerFactory loadBalancerFactory = new LoadBalancerFactory(properties, serverManager);
        LoadBalancer loadBalancer = loadBalancerFactory.getLoadBalancer();
        IntStream.range(1, 111).parallel().forEach(it -> {
            Server server = loadBalancer.getServer();
            server.setWeight(server.getWeight() + Math.sqrt(it));
            logger.info("The request " + it + " sending to the server " + server.getId() + " weight=" + server.getWeight());
        });
    }
}
