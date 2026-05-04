package bg.duosoft.bpo.registers.config;

import bg.duosoft.bpo.common.web.config.AjpConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AjpConfiguration.class})
public class AjpConfig {
}