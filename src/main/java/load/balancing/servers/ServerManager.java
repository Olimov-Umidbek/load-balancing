package load.balancing.servers;

import load.balancing.conf.Properties;
import load.balancing.exception.ServerListOutboundException;
import load.balancing.model.domain.Server;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ServerManager {
    protected final List<Server> serverList;
    private final Properties properties;
    private final ReadWriteLock lock;

    public ServerManager(Properties properties) {
        this.properties = properties;
        this.lock = new ReentrantReadWriteLock();
        this.serverList = new ArrayList<>();
    }

    public void add(Server server) {
        lock.writeLock().lock();
        if (serverList.size() >= properties.serverLimit()) {
            lock.writeLock().unlock();
            throw new ServerListOutboundException("The server list reached to the limit, we cannot add a new server");
        }

        serverList.add(server);
        lock.writeLock().unlock();
    }

    public void remove(Server server) {
        lock.readLock().lock();
        serverList.remove(server);
        lock.readLock().unlock();
    }

    public List<Server> getServerList() {
        return Collections.unmodifiableList(serverList);
    }
}
