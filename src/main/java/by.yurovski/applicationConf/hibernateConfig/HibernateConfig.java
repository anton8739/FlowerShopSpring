package by.yurovski.applicationConf.hibernateConfig;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "by.yurovski")
@EntityScan("by.yurovski")
@EnableTransactionManagement
public class HibernateConfig {

    // properties of data source
  @Value("${jdbc.driverClassName}")
    private String driverClassName;
  @Value("${jdbc.url}")
    private String url;
  @Value("${jdbc.username}")
    private String username;
  @Value("${jdbc.password}")
    private String password;

    // Hibernate's properties
   @Value("${hibernate.dialect}")
    private String hibernateDialect;
   @Value("${hibernate.show_sql}")
    private String hibernateShowSql;
   @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHBM2DDLAuto;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public Properties hibernateProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.hbm2ddl.auto", hibernateHBM2DDLAuto);
        properties.put("hibernate.connection.characterEncoding","UTF-8");
        return properties;
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(dataSource())
                .scanPackages("by.yurovski")
                .addProperties(hibernateProperties())
                .buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        final HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(sessionFactory);
        return htm;
    }
   @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("by.yurovski");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }
}

