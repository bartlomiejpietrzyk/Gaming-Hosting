package pl.bartlomiejpietrzyk.service;

import pl.bartlomiejpietrzyk.dto.UserMessageDto;

public interface MessageService {
    void sendMessage(UserMessageDto message);
}
