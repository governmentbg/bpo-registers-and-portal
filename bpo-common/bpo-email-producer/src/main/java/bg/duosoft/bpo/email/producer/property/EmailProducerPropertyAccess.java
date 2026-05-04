package bg.duosoft.bpo.email.producer.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rabbit-mq")
public class EmailProducerPropertyAccess {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private EmailProperties email;
    private Boolean sslEnabled;

    @Getter
    @Setter
    public static class EmailProperties {
        private String vhost;
        private String routekey;
        private String queueName;
        private String exchangeName;
        private String waitQueueName;
        private int waitQueueTtl;
        private String parkingLotQueueName;
    }

}
