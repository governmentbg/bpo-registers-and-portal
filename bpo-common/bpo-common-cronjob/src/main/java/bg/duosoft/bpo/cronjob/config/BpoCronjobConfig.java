package bg.duosoft.bpo.cronjob.config;

import bg.duosoft.bpo.cronjob.service.BpoMailSenderService;
import bg.duosoft.bpo.email.producer.service.MailQueueService;
import bg.duosoft.cronjob.config.CronjobInitializator;
import bg.duosoft.cronjob.mail.CronjobMailSenderService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * User: ggeorgiev
 * Date: 01.04.2025
 * Time: 15:58
 */
@Configuration
@Import({CronjobInitializator.class})
@ComponentScan("bg.duosoft.bpo.cronjob")
public class BpoCronjobConfig {
    @Bean
    @ConfigurationProperties(prefix="cronjob.datasource")
    public DataSource cronjobDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public CronjobMailSenderService bpoMailSenderService(MailQueueService mailQueueService) {
        return new BpoMailSenderService(mailQueueService);
    }
}
