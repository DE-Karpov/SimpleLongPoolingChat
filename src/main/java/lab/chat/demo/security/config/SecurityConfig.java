package lab.chat.demo.security.config;

import lab.chat.demo.security.filters.TokenAuthFilter;
import lab.chat.demo.security.provider.TokenAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@ComponentScan("lab.chat")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenAuthenticationProvider provider;

    public SecurityConfig(TokenAuthenticationProvider provider) {
        this.provider = provider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/signIn", "/register", "/login", "/signUp", "/swagger-ui.html#/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .addFilterBefore(new TokenAuthFilter(), BasicAuthenticationFilter.class)
                .authenticationProvider(provider)
                .sessionManagement().disable();
    }
}
