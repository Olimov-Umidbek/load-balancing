package load.balancing.servers;

import load.balancing.conf.Properties;
import load.balancing.exception.ServerListOutboundException;
import load.balancing.model.domain.Server;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerManagerTest {
    private final Properties properties = mock(Properties.class);
    private ServerManager serverManager;
    private List<Server> serverList = new CopyOnWriteArrayList<>();
    @Before
    public void before() {
        when(properties.serverLimit()).thenReturn(10);

        serverManager = new ServerManagersFactory(properties).getServerManager();

        serverManager.getServerList().forEach(serverManager::remove);
        IntStream.range(1, 11).forEach(it ->
            serverList.add(
                new Server(it, "address-" + it, it * 1./ 10, it)
            )
        );
    }

    @Test
    public void testAdd() {
        serverList.stream().parallel().forEach(serverManager::add);
        assertEquals(10, serverManager.getServerList().size());
    }

    @Test
    public void testAddWithException() {
        serverList.forEach(serverManager::add);
        ServerListOutboundException exception = assertThrows(ServerListOutboundException.class, () -> {
            serverManager.add(serverList.get(1));
        });

        assertNotNull(exception);
        assertEquals("The server list reached to the limit, we cannot add a new server", exception.getMessage());
    }

    @Test
    public void testRemove() {
        serverList.stream().parallel().forEach(serverManager::add);

        serverManager.remove(serverList.get(2));
    }

    @Test
    public void getServerList() {
        ExecutorService executors = Executors.newFixedThreadPool(5);
        executors.submit(() -> serverList.stream().parallel().forEach(serverManager::add));
        executors.submit(() -> serverList.stream().parallel().forEach(serverManager::remove));
        assertEquals(0, serverManager.getServerList().size());
    }
}