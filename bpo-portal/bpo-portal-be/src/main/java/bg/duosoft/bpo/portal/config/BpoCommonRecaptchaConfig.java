package bg.duosoft.bpo.portal.config;

import bg.duosoft.bpo.recaptcha.config.BpoRecaptchaConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BpoRecaptchaConfig.class)
public class BpoCommonRecaptchaConfig {
}
