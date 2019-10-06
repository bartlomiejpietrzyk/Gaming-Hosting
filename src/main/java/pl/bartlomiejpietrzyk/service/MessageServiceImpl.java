package pl.bartlomiejpietrzyk.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.bartlomiejpietrzyk.dto.UserMessageDto;
import pl.bartlomiejpietrzyk.entity.Message;
import pl.bartlomiejpietrzyk.repository.MessageRepository;
import pl.bartlomiejpietrzyk.repository.UserRepository;

import java.time.LocalDateTime;

public class MessageServiceImpl implements MessageService {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void sendMessage(UserMessageDto contactDto) {
        Message message = new Message();
        if (contactDto.getUserId() != null) {
            message.setUserId(userRepository.getOne(contactDto.getUserId()).getId());
        }
        message.setSubject(contactDto.getSubject());
        message.setText(contactDto.getText());
        message.setTime(LocalDateTime.parse(contactDto.getTime()));
        messageRepository.save(message);
    }
}
