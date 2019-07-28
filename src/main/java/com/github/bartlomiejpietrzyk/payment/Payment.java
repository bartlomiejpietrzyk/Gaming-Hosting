package com.github.bartlomiejpietrzyk.payment;

import com.github.bartlomiejpietrzyk.converter.LocalDateTimeAttributeConverter;
import com.github.bartlomiejpietrzyk.server.Server;
import com.github.bartlomiejpietrzyk.model.BaseEntity;
import com.github.bartlomiejpietrzyk.user.User;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double paymentAmmount;
    private String paymentMethod;

    @CreatedDate
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime paymentDate;

    @ManyToOne
    private Server server;

    @ManyToOne
    private User user;

    public Payment() {
    }

    public Long getId() {
        return this.id;
    }

    Double getPaymentAmmount() {
        return this.paymentAmmount;
    }

    String getPaymentMethod() {
        return this.paymentMethod;
    }

    LocalDateTime getPaymentDate() {
        return this.paymentDate;
    }

    public Server getServer() {
        return this.server;
    }

    public User getUser() {
        return this.user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPaymentAmmount(Double paymentAmmount) {
        this.paymentAmmount = paymentAmmount;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s", paymentAmmount, paymentMethod, paymentDate, server, user);
    }
}
