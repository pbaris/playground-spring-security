package playground.spring.security.providers;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Panos Bariamis (pbaris)
 */
@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        String apiKey = (String) authentication.getPrincipal();

        if (!StringUtils.hasText(apiKey)) {
            throw new InsufficientAuthenticationException("No API key in request");
        }

        if ("pao13".equals(apiKey)) { //TODO proper apikey
            return new ApiKeyAuthenticationToken(apiKey, List.of(new SimpleGrantedAuthority("ADMIN"))); //TODO proper authorities
        }

        throw new BadCredentialsException("API Key is invalid");
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
