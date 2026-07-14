package bg.duosoft.bpo.publik.core.config;

import bg.duosoft.bpo.common.security.util.SecurityUtils;
import bg.duosoft.logging.annotation.EnableLoggingExtras;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

/**
 * User: ggeorgiev
 * Date: 19.10.2022
 * Time: 11:54
 */
@Configuration
@EnableLoggingExtras
public class LoggingExtrasConfig {
    @Bean
    public Supplier<String> loggedUserGetter() {
        return () -> SecurityUtils.getUsername();
    }
}
