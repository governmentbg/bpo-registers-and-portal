package bg.duosoft.bpo.portal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = {"bg.duosoft.bpo.portal.repository", "bg.duosoft.bpo.publik.core.repository", "bg.duosoft.bpo.registers.repository"},
        entityManagerFactoryRef = "bpoRegistersEntityManager",
        transactionManagerRef = "bpoRegistersTransactionManager"

)
@EnableTransactionManagement(order = Ordered.HIGHEST_PRECEDENCE)
public class BpoPortalDatabaseConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean bpoRegistersEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(bpoRegistersDataSource());
        em.setPackagesToScan("bg.duosoft.bpo.portal.entity", "bg.duosoft.bpo.publik.core.entity", "bg.duosoft.bpo.registers.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        em.setJpaProperties(bpoRegistersJpaProperties());
        return em;
    }

    @Bean
    @ConfigurationProperties(prefix = "registers.datasource")
    public DataSource bpoRegistersDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "registers.jpa.properties")
    public Properties bpoRegistersJpaProperties() {
        return new Properties();
    }

    @Bean
    public PlatformTransactionManager bpoRegistersTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(bpoRegistersEntityManager().getObject());
        return transactionManager;
    }

}