package bg.duosoft.bpo.common.client.config;

import org.springframework.context.annotation.Bean;

public class LoggedUserFeignConfig {

    @Bean
    public LoggedUserFeignClientInterceptor requestInterceptor() {
        return new LoggedUserFeignClientInterceptor();
    }
}
