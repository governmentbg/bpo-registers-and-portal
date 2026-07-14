package bg.duosoft.bpo.portal.config;

import bg.duosoft.bpo.email.producer.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableRabbit
@Import(RabbitMQConfig.class)
public class MailProducerConfig {
}
