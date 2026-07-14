package bg.duosoft.bpo.email.producer.config;


import bg.duosoft.bpo.email.producer.property.EmailProducerPropertyAccess;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackages = {"bg.duosoft.bpo.email.producer"})
public class RabbitMQConfig {

    private final EmailProducerPropertyAccess propertyAccess;

    @Bean
    @Primary
    @SneakyThrows
    public ConnectionFactory connectionFactory() {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost(propertyAccess.getHost());
        cf.setPort(propertyAccess.getPort());
        cf.setVirtualHost(propertyAccess.getEmail().getVhost());
        cf.setUsername(propertyAccess.getUsername());
        cf.setPassword(propertyAccess.getPassword());

        if (propertyAccess.getSslEnabled()) {
            cf.useSslProtocol();
        }
        return cf;
    }

    @Bean
    @Primary
    public CachingConnectionFactory cachingConnectionFactory() {
        return new CachingConnectionFactory(connectionFactory());
    }

    @Bean
    @Primary
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory());
        rabbitTemplate.setChannelTransacted(true);
        return rabbitTemplate;
    }

    @Bean
    @Primary
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(cachingConnectionFactory());
    }

    @Bean
    @Primary
    public Queue primaryQueue() {
        return QueueBuilder.durable(propertyAccess.getEmail().getQueueName())
                .deadLetterExchange(propertyAccess.getEmail().getExchangeName())
                .deadLetterRoutingKey(propertyAccess.getEmail().getWaitQueueName())
                .build();
    }

    @Bean
    @Primary
    public Queue waitQueue() {
        return QueueBuilder.durable(propertyAccess.getEmail().getWaitQueueName())
                .deadLetterExchange(propertyAccess.getEmail().getExchangeName())
                .deadLetterRoutingKey(propertyAccess.getEmail().getRoutekey())
                .ttl(propertyAccess.getEmail().getWaitQueueTtl())
                .build();
    }

    @Bean
    @Primary
    public Queue parkingLotQueue() {
        return new Queue(propertyAccess.getEmail().getParkingLotQueueName());
    }

    @Bean
    @Primary
    public DirectExchange exchange() {
        return new DirectExchange(propertyAccess.getEmail().getExchangeName());
    }

    @Bean
    @Primary
    public Binding emailBinding() {
        return BindingBuilder.bind(primaryQueue()).to(exchange()).with(propertyAccess.getEmail().getRoutekey());
    }

    @Bean
    @Primary
    public Binding waitBinding() {
        return BindingBuilder.bind(waitQueue()).to(exchange()).with(propertyAccess.getEmail().getWaitQueueName());
    }

    @Bean
    @Primary
    public Binding parkingBinding() {
        return BindingBuilder.bind(parkingLotQueue()).to(exchange()).with(propertyAccess.getEmail().getParkingLotQueueName());
    }


    @Bean
    ApplicationRunner runner(AmqpAdmin rabbitAdmin) {
        return args -> rabbitAdmin.initialize();
    }

}
