package bg.duosoft.bpo.receiptservice.config;

import bg.duosoft.bpo.receiptservice.util.CustomTemplateExceptionHandler;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import java.io.File;
import java.io.IOException;

@Configuration
@ComponentScan({"bg.duosoft.bpo.receiptservice"})
@RequiredArgsConstructor
public class FreeMarkerConfig {

    private final Environment environment;

    @Bean
    @Primary
    public freemarker.template.Configuration freeMarkerConfigurationCustom() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_30);
        cfg.setTemplateExceptionHandler(new CustomTemplateExceptionHandler());
        cfg.setDefaultEncoding("UTF-8");
        ClassTemplateLoader templateLoader = new ClassTemplateLoader(getClass(), "/templates/freemarker/");
        cfg.setTemplateLoader(templateLoader);
        return cfg;
    }
}