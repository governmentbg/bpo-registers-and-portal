package bg.duosoft.bpo.publik.core.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Data
public class MinioConfigTemp {
    @Value("${minio.bucket.temp}")
    private String tempBucket;
}
