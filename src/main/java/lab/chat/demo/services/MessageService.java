package lab.chat.demo.services;

import lab.chat.demo.forms.MessageForm;
import lab.chat.demo.transfer.MessageDto;
import lab.chat.demo.transfer.UserDto;
import lab.chat.demo.models.User;

import java.util.List;

public interface MessageService {
    void receiveMessage(MessageForm messageForm, User user);

    List<MessageDto> getMessagesForPage(UserDto dto);
}
