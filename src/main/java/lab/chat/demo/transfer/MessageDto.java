package lab.chat.demo.transfer;

import lab.chat.demo.models.Message;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {

    private String login;
    private String text;

    public static MessageDto form(Message message) {
        return MessageDto.builder()
                .login(message.getUser().getLogin())
                .text(message.getText())
                .build();
    }
}
