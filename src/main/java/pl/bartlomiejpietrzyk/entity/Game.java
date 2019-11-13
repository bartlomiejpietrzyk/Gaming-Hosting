package pl.bartlomiejpietrzyk.entity;

import lombok.Getter;
import lombok.Setter;

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
    @OneToMany(mappedBy = "game")
    private Set<Server> servers = new HashSet<>();
//
//    protected Game() {
//    }
//
//    public Game(GameBuilder builder) {
//        this.title = builder.title;
//        this.description = builder.description;
//        this.publicSlotPrice = builder.publicSlotPrice;
//        this.privateSlotPrice = builder.privateSlotPrice;
//        this.maxPlayers = builder.maxPlayers;
//    }
//
//    public static GameBuilder builder() {
//        return new GameBuilder();
//    }
//
//   public static class GameBuilder {
//        private String title = "Title";
//        private String description = "Description";
//        private Double publicSlotPrice = 1.5;
//        private Double privateSlotPrice = 1.0;
//        private Integer maxPlayers = 64;
//
//        public GameBuilder setGameTitle(final String title) {
//            this.title = title;
//            return this;
//        }
//
//        public GameBuilder setGameDescription(final String description) {
//            this.description = description;
//            return this;
//        }
//
//        public GameBuilder setGamePublicSlotPrice(final Double slotPrice) {
//            this.publicSlotPrice = slotPrice;
//            return this;
//        }
//
//        public GameBuilder setGamePrivateSlotPrice(final Double slotPrice) {
//            this.privateSlotPrice = slotPrice;
//            return this;
//        }
//
//        public GameBuilder setGameMaxPlayers(final Integer maxPlayers) {
//            this.maxPlayers = maxPlayers;
//            return this;
//        }
//
//        public Game build() {
//            return new Game(this);
//        }
//    }
}
