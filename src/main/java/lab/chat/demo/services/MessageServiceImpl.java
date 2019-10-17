package lab.chat.demo.services;

import lab.chat.demo.transfer.MessageDto;
import lab.chat.demo.transfer.UserDto;
import lab.chat.demo.models.Message;
import lab.chat.demo.models.User;
import lab.chat.demo.repositories.MessagesRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lab.chat.demo.transfer.MessageDto.*;

@Service
public class MessageServiceImpl implements MessageService {
    private final Map<String, List<Message>> messages = new HashMap<>();
    private final MessagesRepository repository;

    @Autowired
    public MessageServiceImpl(MessagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void receiveMessage(MessageDto message, User user) {
        if (!messages.containsKey(user.getLogin())) {
            messages.put(user.getLogin(), new ArrayList<>());
        }
        Message messageEntity = Message.builder()
                .text(message.getText())
                .user(user)
                .build();
        repository.save(messageEntity);
        for (List<Message> userMessages : messages.values()) {
            synchronized (userMessages) {
                userMessages.add(messageEntity);
                userMessages.notifyAll();
            }
        }
    }

    @SneakyThrows
    @Override
    public List<MessageDto> getMessagesForPage(UserDto dto) {
        synchronized (messages.get(dto.getLogin())) {
            if (messages.get(dto.getLogin()).isEmpty()) {
                messages.get(dto.getLogin()).wait();
            }
            List<Message> messageResponse = new ArrayList<>(repository.findAll());
            List<MessageDto> response = new ArrayList<>();
            for (Message message : messageResponse) {
                response.add(builder()
                        .tokenValue(message.getUser().getTokens().stream().findFirst().get().getValue())
                        .text(message.getText())
                        .build());
            }
            messages.get(dto.getLogin()).clear();
            return response;
        }
    }
}
