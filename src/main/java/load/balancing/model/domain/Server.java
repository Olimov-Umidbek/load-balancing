package load.balancing.model.domain;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Server {
    private final int id;
    private final String address;
    private Double weight;
    private int connectionTime;
    private ReadWriteLock weightLock = new ReentrantReadWriteLock();
    private ReadWriteLock connectionLock = new ReentrantReadWriteLock();

    public Server(int id, String address, Double weight, int connectionTime) {
        this.id = id;
        this.address = address;
        this.weight = weight;
        this.connectionTime = connectionTime;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        weightLock.readLock().lock();
        this.weight = weight;
        weightLock.readLock().unlock();
    }

    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        connectionLock.readLock().lock();
        this.connectionTime = connectionTime;
        connectionLock.readLock().unlock();
    }

    @Override
    public String toString() {
        return "Server{" +
            "id=" + id +
            ", address='" + address + '\'' +
            ", weight=" + weight +
            ", connectionTime=" + connectionTime +
            '}';
    }
}
