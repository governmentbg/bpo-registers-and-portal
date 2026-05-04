package bg.duosoft.bpo.common.client.config;

import bg.duosoft.bpo.common.security.util.SecurityUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggedUserFeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if(SecurityUtils.isUserAuthenticated()) {
            requestTemplate.header("Authorization", "Bearer " + SecurityUtils.getAccessToken());
        }
    }
}
