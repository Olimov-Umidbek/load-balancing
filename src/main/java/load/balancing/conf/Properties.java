package load.balancing.conf;

import load.balancing.model.enums.LoadBalancerAlgorithm;

public record Properties(
    int serverLimit,
    LoadBalancerAlgorithm algorithm
) {

}
