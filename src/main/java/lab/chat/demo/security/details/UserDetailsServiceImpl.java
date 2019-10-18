package lab.chat.demo.security.details;

import lab.chat.demo.models.Token;
import lab.chat.demo.repositories.TokensRepository;
import lab.chat.demo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final TokensRepository repository;

    @Autowired
    public UserDetailsServiceImpl(TokensRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
        Optional<Token> authenticationCandidate = repository.findOneByValue(value);
        if (authenticationCandidate.isPresent()) {
            Token token = authenticationCandidate.get();
            return new UserDetailsImpl(token.getUser(), token);
        }
        return null;
    }
}
