package playground.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Panos Bariamis (pbaris)
 */
@Configuration
@Order(2)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class StoreFrontConfigurationAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api-sf/**")
            .authorizeRequests()
//                .accessDecisionManager(accessDecisionManager())
//                .antMatchers(HttpMethod.OPTIONS, "/api-sf/**").permitAll()
//                .antMatchers("/api-sf/customers/register").authenticated()
//                .antMatchers("/api-sf/shell").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic()
//                .and()
//                .cors().and().csrf().disable()
        ;
    }

    /*@Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();
        ExpressionBasedPreInvocationAdvice expressionAdvice = new ExpressionBasedPreInvocationAdvice();
        expressionAdvice.setExpressionHandler(new DefaultMethodSecurityExpressionHandler());
        decisionVoters.add(new PreInvocationAuthorizationAdviceVoter(expressionAdvice));

        decisionVoters.add(new Jsr250Voter());

        RoleVoter roleVoter = new RoleVoter();
        roleVoter.setRolePrefix("ROLE_");
        decisionVoters.add(roleVoter);
        decisionVoters.add(new AuthenticatedVoter());

        WebExpressionVoter expressionVoter = new WebExpressionVoter();
        expressionVoter.setExpressionHandler(getExpressionHandler(http));
        decisionVoters.add(expressionVoter);

        return new AffirmativeBased(decisionVoters);
    }*/
}
