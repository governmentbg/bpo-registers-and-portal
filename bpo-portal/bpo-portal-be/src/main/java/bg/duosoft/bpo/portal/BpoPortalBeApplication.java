package bg.duosoft.bpo.portal;

import bg.duosoft.bpo.common.web.util.AppInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BpoPortalBeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BpoPortalBeApplication.class, args);
        AppInfo.logApplicationInfo(context);

    }
}
