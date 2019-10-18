package lab.chat.demo.services;

import lab.chat.demo.forms.UserForm;
import lab.chat.demo.models.Token;
import lab.chat.demo.models.User;
import lab.chat.demo.repositories.TokensRepository;
import lab.chat.demo.repositories.UsersRepository;
import lab.chat.demo.transfer.TokenDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static lab.chat.demo.transfer.TokenDto.*;

@Service
public class LoginServiceImpl implements LoginService {

    private final TokensRepository tokensRepository;
    private final UsersRepository usersRepository;

    private final PasswordEncoder encoder;

    @Autowired
    public LoginServiceImpl(TokensRepository tokensRepository, PasswordEncoder encoder, UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.tokensRepository = tokensRepository;
        this.encoder = encoder;
    }

    @Override
    public TokenDto login(UserForm form) {
        Optional<User> userCandidate = usersRepository.findOneByLogin(form.getLogin());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            if (encoder.matches(form.getPassword(), user.getHashPassword())) {
                Token token = Token.builder()
                        .user(user)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();

                tokensRepository.save(token);
                return from(token);
            }
        }
        throw new IllegalArgumentException("User not found");
    }
}
