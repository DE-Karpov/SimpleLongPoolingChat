package lab.chat.demo.security.filters;

import lab.chat.demo.security.authentication.TokenAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TokenAuthFilter implements Filter {

    private final static String AUTH_HEADER = "AUTH";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        String tokenValue = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AUTH_HEADER)) {
                    tokenValue = cookie.getValue();
                }
            }
        }
        if (tokenValue != null) {
            TokenAuthentication authentication = new TokenAuthentication(tokenValue);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
