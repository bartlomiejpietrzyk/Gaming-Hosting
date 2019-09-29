package pl.bartlomiejpietrzyk.hosting;

import pl.bartlomiejpietrzyk.server.Server;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hosting")
public class Hosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String country;
    private LocalDateTime startCooperation;
    private Long maxCapacity;
    @OneToMany
    private Set<Server> servers = new HashSet<>();

    public Hosting() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String getCountry() {
        return this.country;
    }

    public Set<Server> getServers() {
        return this.servers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setServers(Set<Server> servers) {
        this.servers = servers;
    }

    public LocalDateTime getStartCooperation() {
        return startCooperation;
    }

    public void setStartCooperation(LocalDateTime startCooperation) {
        this.startCooperation = startCooperation;
    }

    public Long getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Long maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", name, city, country);
    }
}
