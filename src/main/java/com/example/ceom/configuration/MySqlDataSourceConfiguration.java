package com.example.ceom.configuration;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.ceom.repository.mysql",
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef= "mysqlTransactionManager")
public class MySqlDataSourceConfiguration {

    public static final String MYSQL = "mysql";
    @Bean
    @ConfigurationProperties("app.datasource.mysql")
    public DataSourceProperties mysqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.mysql.configuration")
    public DataSource mysqlDataSource() {
        return mysqlDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Bean(name = "mysqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
//        Map<String, Object> properties = new HashMap<String, Object>();
//        properties.put("hibernate.hbm2ddl.auto", "update");
        return builder
                .dataSource(mysqlDataSource())
                .packages("com.example.ceom.model.mysql")
//                .persistenceUnit(MYSQL)
//                .properties(properties)
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager mysqlTransactionManager(
            final @Qualifier("mysqlEntityManagerFactory") LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(mysqlEntityManagerFactory.getObject()));
    }

//    @Bean
//    @Primary
//    public PlatformTransactionManager mysqlTransactionManager(
//            final @Qualifier("mysqlEntityManagerFactory") LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory) {
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//        jpaTransactionManager.setDataSource(mysqlDataSource());
//        jpaTransactionManager.setPersistenceUnitName(MYSQL);
//        return jpaTransactionManager;
//    }

//    @Bean(name = "externalTransactionManager")
//    public PlatformTransactionManager externalTransactionManager() {
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//        jpaTransactionManager.setDataSource(externalDataSource());
//        jpaTransactionManager.setPersistenceUnitName(EXTERNAL);
//        return jpaTransactionManager;
//    }


}
