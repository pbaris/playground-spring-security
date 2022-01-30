package playground.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import playground.spring.security.providers.ApiKeyAuthenticationFilter;
import playground.spring.security.providers.ApiKeyAuthenticationProvider;
import playground.spring.security.providers.UsernamePasswordAuthenticationProvider;

/**
 * @author Panos Bariamis (pbaris)
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    @Autowired
    private ApiKeyAuthenticationProvider apiKeyAuthenticationProvider;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernamePasswordAuthenticationProvider)
            .authenticationProvider(apiKeyAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .httpBasic()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(new ApiKeyAuthenticationFilter(authenticationManager()))
            .authorizeRequests()
            .anyRequest()
            .authenticated()
        ;
    }
}
