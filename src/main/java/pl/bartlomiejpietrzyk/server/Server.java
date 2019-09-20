package pl.bartlomiejpietrzyk.server;

import org.springframework.data.annotation.CreatedDate;
import pl.bartlomiejpietrzyk.converter.LocalDateTimeAttributeConverter;
import pl.bartlomiejpietrzyk.game.Game;
import pl.bartlomiejpietrzyk.hosting.Hosting;
import pl.bartlomiejpietrzyk.payment.Payment;
import pl.bartlomiejpietrzyk.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "server")
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;
    private String name;
    private String type;
    private String slot;
    private Boolean active;
    private Boolean isPaid;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime rentStart;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime rentExpire;

    @ManyToOne
    private User user;

    @ManyToOne
    private Hosting hosting;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "server")
    private List<Payment> payments = new ArrayList<>();

    private String paymentStatus;
    private String serverOwner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public LocalDateTime getRentStart() {
        return rentStart;
    }

    public void setRentStart(LocalDateTime rentStart) {
        this.rentStart = rentStart;
    }

    public LocalDateTime getRentExpire() {
        return rentExpire;
    }

    public void setRentExpire(LocalDateTime rentExpire) {
        this.rentExpire = rentExpire;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hosting getHosting() {
        return hosting;
    }

    public void setHosting(Hosting hosting) {
        this.hosting = hosting;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getServerOwner() {
        return serverOwner;
    }

    public void setServerOwner(String serverOwner) {
        this.serverOwner = serverOwner;
    }

}
