package playground.spring.security.providers;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Panos Bariamis (pbaris)
 */
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();

        if (!StringUtils.hasText(username)) {
            throw new BadCredentialsException("invalid login details");
        }

        final String password = authentication.getCredentials().toString();

        // Authentication failed
        if (!testCredentials(username, password)) {
            return null;
        }

        //TODO proper authorities
        return new UsernamePasswordAuthenticationToken(username, password, List.of(new SimpleGrantedAuthority("ADMIN")));
    }

    private boolean testCredentials(final String username, final String password) {
        //TODO implement this based on a service
        return "pao".equalsIgnoreCase(username) && "pao13".equalsIgnoreCase(password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
