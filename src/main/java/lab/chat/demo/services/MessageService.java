package lab.chat.demo.services;

import lab.chat.demo.transfer.MessageDto;
import lab.chat.demo.transfer.UserDto;
import lab.chat.demo.models.User;

import java.util.List;

public interface MessageService {
    void receiveMessage(MessageDto messageDto, User user);

    List<MessageDto> getMessagesForPage(UserDto dto);
}
