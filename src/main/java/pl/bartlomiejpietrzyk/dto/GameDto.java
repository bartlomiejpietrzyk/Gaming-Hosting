package pl.bartlomiejpietrzyk.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bartlomiejpietrzyk.entity.Game;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter

public class GameDto {
    private Long id;
    @NotEmpty(message = "Set title!")
    private String title;

    @NotEmpty(message = "Set description!")
    private String description;

    @NotEmpty(message = "Set public slot price!")
    private Double publicSlotPrice;

    @NotEmpty(message = "Set maximum public slots!")
    private Integer publicMaxSlot;

    @NotEmpty(message = "Set minimum public slots!")
    private Integer publicMinSlot;

    @NotEmpty(message = "Set private slot price!")
    private Double privateSlotPrice;

    @NotEmpty(message = "Set maximum private slots!")
    private Integer privateMaxSlot;

    @NotEmpty(message = "Set minimum private slots!")
    private Integer privateMinSlot;

    private Boolean available;
    private Boolean liveStreamTvSlot;

    @NotEmpty(message = "Set TV slot price!")
    private Double liveStreamTvSlotPrice;


    @Autowired
    public GameDto() {
    }

    public GameDto(Game that) {
        this.id = that.getId();
        this.title = that.getTitle();
        this.description = that.getDescription();
        this.publicSlotPrice = that.getPublicSlotPrice();
        this.privateSlotPrice = that.getPrivateSlotPrice();
        this.privateMaxSlot = that.getPrivateMaxSlot();
        this.privateMinSlot = that.getPrivateMinSlot();
        this.publicMaxSlot = that.getPublicMaxSlot();
        this.publicMinSlot = that.getPublicMinSlot();
        this.available = that.getAvailable();
        this.liveStreamTvSlot = that.getLiveStreamTvSlot();
        this.liveStreamTvSlotPrice = that.getLiveStreamTvSlotPrice();
    }
}
