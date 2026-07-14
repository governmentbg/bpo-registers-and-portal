package bg.duosoft.bpo.registers;

import bg.duosoft.bpo.common.web.util.AppInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BpoRegistersBeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BpoRegistersBeApplication.class, args);
        AppInfo.logApplicationInfo(run);
    }

}
