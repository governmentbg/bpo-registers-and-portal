package bg.duosoft.bpo.common.security.config;

import bg.duosoft.bpo.common.security.CustomJwtGrantedAuthoritiesConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.ObjectUtils;

/**
 * User: ggeorgiev
 * Date: 12.01.2024
 * Time: 17:34
 */
@Configuration
@Order(10)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtSecurityConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.principal-claim-name:#{null}}")
    private String principalClaimName;
    @Bean
    public SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
        http

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll())
                .oauth2ResourceServer(oauth2ResourceServer -> {
                    oauth2ResourceServer.jwt(jwtConfigurer -> {
                        jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter());
                    });
                });

        return http.build();
    }
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        CustomJwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new CustomJwtGrantedAuthoritiesConverter();
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        if (!ObjectUtils.isEmpty(principalClaimName)) {
            jwtAuthenticationConverter.setPrincipalClaimName(principalClaimName);
        }
        return jwtAuthenticationConverter;
    }
}
