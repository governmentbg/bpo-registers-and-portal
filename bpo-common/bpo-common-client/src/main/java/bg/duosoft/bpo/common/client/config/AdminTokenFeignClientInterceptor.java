package bg.duosoft.bpo.common.client.config;

import bg.duosoft.bpo.common.security.TokenManager;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AdminTokenFeignClientInterceptor implements RequestInterceptor {

    @Autowired(required = false)
    private TokenManager tokenManager;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if(tokenManager != null){
            requestTemplate.header("Authorization", "Bearer " + tokenManager.getAccessToken());
        } else {
            log.error("TokenManager should not be null if you want to call admin clients and pass Authorization header");
        }
    }
}
