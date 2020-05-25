package lab.chat.demo.transfer;

import lab.chat.demo.models.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TokenDto {

    private String value;

    public static TokenDto from(Token token) {
        return new TokenDto(token.getValue());
    }
}
