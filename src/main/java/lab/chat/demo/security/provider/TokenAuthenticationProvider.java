package lab.chat.demo.security.provider;

import lab.chat.demo.models.Token;
import lab.chat.demo.repositories.TokensRepository;
import lab.chat.demo.security.details.UserDetailsImpl;
import lab.chat.demo.security.authentication.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService service;

    @Autowired
    public TokenAuthenticationProvider(@Qualifier("userDetailsServiceImpl") UserDetailsService service) {
        this.service = service;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        UserDetailsImpl details = (UserDetailsImpl) service.loadUserByUsername(tokenAuthentication.getName());
        if (details != null) {
            tokenAuthentication.setDetails(details);
            tokenAuthentication.setAuthenticated(true);
        } else throw new BadCredentialsException("Bad token");
        return tokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
