package bg.duosoft.bpo.common.client.config;

import org.springframework.context.annotation.Bean;

public class AdminTokenFeignConfig {

    @Bean
    public AdminTokenFeignClientInterceptor clientTokenFeignClientInterceptor() {
        return new AdminTokenFeignClientInterceptor();
    }
}
