package playground.spring.security.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @see <a href="https://github.com/keycloak/keycloak/issues/8857">Keycloak Circular Reference Issue</a>
 * @author Panos Bariamis (pbaris)
 */
@Configuration
public class KeycloakResolverConfiguration {

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
}
