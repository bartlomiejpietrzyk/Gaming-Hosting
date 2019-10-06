package pl.bartlomiejpietrzyk.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bartlomiejpietrzyk.entity.Message;

import java.time.format.DateTimeFormatter;

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
        this.time = that.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.status = that.getStatus();
    }
}
