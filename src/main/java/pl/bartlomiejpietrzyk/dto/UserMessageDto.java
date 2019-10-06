package pl.bartlomiejpietrzyk.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bartlomiejpietrzyk.GamingHostingApplication;
import pl.bartlomiejpietrzyk.entity.Message;

@Getter
@Setter
public class UserMessageDto {
    private Long id;
    private Long userId;
    private String subject;
    private String text;
    private String time;
    private Boolean status;

    @Autowired
    public UserMessageDto() {
    }

    public UserMessageDto(Message that) {
        this.id = that.getId();
        this.userId = that.getUserId();
        this.subject = that.getSubject();
        this.text = that.getText();
        this.time = that.getTime().format(GamingHostingApplication.FORMATTER);
        this.status = that.getStatus();
    }
}
