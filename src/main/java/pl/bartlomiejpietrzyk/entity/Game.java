package pl.bartlomiejpietrzyk.entity;

import lombok.Getter;
import lombok.Setter;
import pl.bartlomiejpietrzyk.GameType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Double publicSlotPrice;
    private Double privateSlotPrice;
    private Integer publicMaxSlot;
    private Integer publicMinSlot;
    private Integer privateMaxSlot;
    private Integer privateMinSlot;
    private Boolean available;
    private Boolean liveStreamTvSlot;
    private Double liveStreamTvSlotPrice;
    private GameType type;
    @OneToMany(mappedBy = "game")
    private Set<Server> servers = new HashSet<>();

}
