package load.balancing.servers;

import load.balancing.conf.Properties;

public class ServerManagersFactory {

    private final Properties properties;

    public ServerManagersFactory(Properties properties) {
        this.properties = properties;
    }

    public ServerManager getServerManager() {
        return new ServerManager(properties);
    }
}
