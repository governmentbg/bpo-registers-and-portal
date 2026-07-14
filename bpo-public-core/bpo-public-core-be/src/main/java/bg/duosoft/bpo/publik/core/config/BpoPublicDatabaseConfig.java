package bg.duosoft.bpo.publik.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@EnableJpaRepositories(
        basePackages = "bg.duosoft.bpo.publik.core.repository",
        entityManagerFactoryRef = "bpoPublicEntityManager",
        transactionManagerRef = "bpoPublicTransactionManager"
)
@EnableTransactionManagement(order = Ordered.HIGHEST_PRECEDENCE)
public class BpoPublicDatabaseConfig {

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean bpoPublicEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(bpoPublicDataSource());
        em.setPackagesToScan("bg.duosoft.bpo.publik.core.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        em.setJpaProperties(bpoRegistersJpaProperties());

        return em;
    }

    @Bean
    @ConfigurationProperties(prefix = "registers.jpa.properties")
    public Properties bpoRegistersJpaProperties() {
        return new Properties();
    }
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "registers.datasource")
    public DataSource bpoPublicDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager bpoPublicTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(bpoPublicEntityManager().getObject());
        return transactionManager;
    }

}