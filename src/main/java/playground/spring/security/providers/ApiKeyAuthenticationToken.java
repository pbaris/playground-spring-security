package playground.spring.security.providers;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;

/**
 * A marker for Authentications that should never be stored across requests, for example a bearer token authentication
 *
 * @author Panos Bariamis (pbaris)
 */
@Transient
public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {

    private final String apiKey;

    public ApiKeyAuthenticationToken(String apiKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.apiKey = apiKey;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return apiKey;
    }

    @Override
    public Object getPrincipal() {
        return apiKey;
    }
}
