package playground.spring.security.providers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 * @author Panos Bariamis (pbaris)
 */
public class ApiKeyAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    public ApiKeyAuthenticationFilter(final AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader("x-api-key"); //TODO proper header
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }
}
