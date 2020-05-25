package lab.chat.demo.transfer;

import lab.chat.demo.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String login;
    private String password;

    public static UserDto form(User user) {
        return UserDto.builder()
                .login(user.getLogin())
                .password(user.getHashPassword())
                .build();

    }
}
