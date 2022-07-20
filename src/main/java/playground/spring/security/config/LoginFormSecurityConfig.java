package playground.spring.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Panos Bariamis (pbaris)
 */
@Configuration
@ConditionalOnProperty(value = "security.login-form", havingValue = "true")
@EnableWebSecurity
public class LoginFormSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("s").password(passwordEncoder().encode("s")).roles("USER")
            .and()
            .withUser("a").password(passwordEncoder().encode("a")).roles("ADMIN");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.authorizeRequests()
            .antMatchers("/login", "/index", "/").permitAll()
            .antMatchers("/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/admin/**").hasAnyRole("ADMIN")
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/process-login")
            .defaultSuccessUrl("/index", false)
            .failureUrl("/login?error=true")
            .permitAll()
            .and()
            .logout()
            .logoutSuccessUrl("/login?logout=true")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll()
            .and()
            .csrf()
            .disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**", "/scss/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
