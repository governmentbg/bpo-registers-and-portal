package bg.duosoft.bpo.publik.core;

import bg.duosoft.bpo.common.web.util.AppInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BpoPublicCoreBeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BpoPublicCoreBeApplication.class, args);
        AppInfo.logApplicationInfo(run);
    }

}
