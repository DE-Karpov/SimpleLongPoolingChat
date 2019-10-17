package lab.chat.demo.security.authentication;

import lab.chat.demo.security.details.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class TokenAuthentication implements Authentication {

    private String token;
    private boolean isAuthenticated;
    private UserDetailsImpl details;

    public TokenAuthentication(String token) {
        this.token = token;
    }

    public void setDetails(UserDetailsImpl details) {
        this.details = details;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return details != null ? details.getAuthorities() : null;
    }

    @Override
    public Object getCredentials() {
        return details != null ? details.getPassword() : null;
    }

    @Override
    public UserDetails getDetails() {
        return details;
    }

    @Override
    public Object getPrincipal() {
        return details != null ? details.getUser() : null;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
