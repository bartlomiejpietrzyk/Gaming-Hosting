package pl.bartlomiejpietrzyk.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.bartlomiejpietrzyk.game.GameRepository;
import pl.bartlomiejpietrzyk.hosting.HostingRepository;
import pl.bartlomiejpietrzyk.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Random;

@Controller
public class ServerService {
    private final String hostname = "@HeadMasters.com";
    private ServerRepository serverRepository;
    private HostingRepository hostingRepository;
    private GameRepository gameRepository;
    private UserRepository userRepository;

    @Autowired
    public ServerService(ServerRepository serverRepository, HostingRepository hostingRepository,
                         GameRepository gameRepository, UserRepository userRepository) {
        this.serverRepository = serverRepository;
        this.hostingRepository = hostingRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public String ipGenerator() {
        Random r = new Random();
        return r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
    }

    public String count() {
        return String.valueOf(serverRepository.count());
    }

    public void deleteServer(String id) {
        serverRepository.delete(Long.valueOf(id));
    }

    public Server orderServer(ServerOrderDto orderedServer) {
        Server server = new Server();
        server.setPaid(false);
        server.setActive(false);
        server.setIp(ipGenerator());
        server.setRentStart(LocalDateTime.now());
        server.setRentExpire(LocalDateTime.now().plusMonths(Long.parseLong(orderedServer.getRentExpire())));
        return serverRepository.save(setServerFromDtoOrderForm(server, orderedServer));
    }

    public Server setServerFromDtoOrderForm(Server server, ServerOrderDto serverOrderDto) {
        server.setName(serverOrderDto.getName());
        server.setType(serverOrderDto.getType());
        server.setGame(gameRepository.findOne(Long.valueOf(serverOrderDto.getId())));
        server.setUser(userRepository.getOne(Long.valueOf(serverOrderDto.getUser())));
        server.setPaid(Boolean.valueOf(serverOrderDto.getPaymentStatus()));
        server.setActive(Boolean.valueOf(serverOrderDto.getActive()));
        server.setIp(serverOrderDto.getIp());
        server.setHosting(hostingRepository.getOne(Long.valueOf(serverOrderDto.getHosting())));
        server.setRentStart(LocalDateTime.parse(serverOrderDto.getRentStart()));
        server.setRentExpire(LocalDateTime.parse(serverOrderDto.getRentExpire()));
        server.setSlot(serverOrderDto.getSlot());
        return server;
    }

}
