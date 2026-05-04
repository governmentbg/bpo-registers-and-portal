package bg.duosoft.bpo.common.web.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Order(9)
public class SwaggerAuthenticationConfig {

    @Value("${swagger-ui.basic.auth.user:swagger}")
    private String basicAuthUser;

    @Value("${swagger-ui.basic.auth.pass:$2a$10$ht58i3nglYeatzl79KL8fODj5VRhuvevlAPFYMLWOzYwm/w/ZT3Di}") //swagger
    private String basicAuthPass;

    @Value("${swagger-ui.basic.auth.role:swagger}")
    private String basicAuthRole;



    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.builder().username(basicAuthUser).password(basicAuthPass).roles(basicAuthRole).build());
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain swaggerFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/swagger-ui/**", "/api-docs/**");
        http
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/swagger-ui/**").hasRole(basicAuthRole))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api-docs/**").hasRole(basicAuthRole))
                .httpBasic(withDefaults())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(withDefaults()));
        return http.build();
    }


}