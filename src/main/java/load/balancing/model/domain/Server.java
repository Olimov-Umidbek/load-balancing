package load.balancing.model.domain;

public class Server {
    private final String address;
    private final Double weight;
    private final int connectionTime;

    public Server(String address, Double weight, int connectionTime) {
        this.address = address;
        this.weight = weight;
        this.connectionTime = connectionTime;
    }

    public String getAddress() {
        return address;
    }

    public Double getWeight() {
        return weight;
    }

    public int getConnectionTime() {
        return connectionTime;
    }
}
