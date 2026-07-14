package bg.duosoft.bpo.publik.core.config;

import bg.duosoft.bpo.fileservice.property.FileConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * User: ggeorgiev
 * Date: 29.01.2024
 * Time: 13:45
 */
@Configuration
@ComponentScan("bg.duosoft.bpo.fileservice")
public class FileServiceConfig {
    @Bean
    @ConfigurationProperties("file.filegroupconfig")
    public Map<String, FileConfig> fileGroupConfig() {
        return new HashMap<>();
    }
}
