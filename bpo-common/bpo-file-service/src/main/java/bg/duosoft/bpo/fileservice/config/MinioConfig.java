package bg.duosoft.bpo.fileservice.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.06.2022
 * Time: 15:40
 */
@Configuration
@ComponentScan({"bg.duosoft.bpo.fileservice"})
public class MinioConfig {

    @Value("${minio.public.endpoint}")
    private String minioEndpoint;

    @Value("${minio.public.user}")
    private String minioUsername;

    @Value("${minio.public.pass}")
    private String minioPassword;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder().endpoint(minioEndpoint).credentials(minioUsername, minioPassword).build();
        minioClient.setTimeout(1000, 5000, 5000);
        return minioClient;
    }
}
