package com.example.ceom.configuration;
import org.apache.commons.dbcp.BasicDataSource;
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
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.ceom.repository.auth",
        entityManagerFactoryRef = "authEntityManagerFactory",
        transactionManagerRef= "authTransactionManager")
public class AuthDataSourceConfiguration {

    public static final String MYSQL = "mysql";
    @Bean
    @ConfigurationProperties("app.datasource.auth")
    public DataSourceProperties authDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.auth.configuration")
    public DataSource authDataSource() {
        return authDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Bean(name = "authEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean authEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("show-sql", "true");
        return builder
                .dataSource(authDataSource())
                .packages("com.example.ceom.model.auth")
                .persistenceUnit(MYSQL)
                .properties(properties)
                .build();
    }

    @Bean
//    @Primary
    public PlatformTransactionManager authTransactionManager(
            final @Qualifier("authEntityManagerFactory") LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory) {
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
