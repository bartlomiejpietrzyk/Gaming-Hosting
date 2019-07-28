package com.github.bartlomiejpietrzyk.game;

import com.github.bartlomiejpietrzyk.server.Server;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private Double publicSlotPrice;
    @NotNull
    private Double privateSlotPrice;
    @NotNull
    private Integer maxPlayers;
    @OneToMany(mappedBy = "game")
    private Set<Server> servers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPublicSlotPrice() {
        return publicSlotPrice;
    }

    public void setPublicSlotPrice(Double publicSlotPrice) {
        this.publicSlotPrice = publicSlotPrice;
    }

    public Double getPrivateSlotPrice() {
        return privateSlotPrice;
    }

    public void setPrivateSlotPrice(Double privateSlotPrice) {
        this.privateSlotPrice = privateSlotPrice;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Set<Server> getServers() {
        return servers;
    }

    public void setServers(Set<Server> servers) {
        this.servers = servers;
    }

}
