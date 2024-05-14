package com.example.ceom.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.ceom.repository.sqlserver",
        entityManagerFactoryRef = "sqlserverEntityManagerFactory",
        transactionManagerRef= "sqlserverTransactionManager")
public class SqlServerDataSourceConfiguration {
    @Bean
    @ConfigurationProperties("app.datasource.sqlserver")
    public DataSourceProperties sqlserverDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.sqlserver.configuration")
    public DataSource sqlserverDataSource() {
        return sqlserverDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Bean(name = "sqlserverEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sqlserverEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(sqlserverDataSource())
                .packages("com.example.ceom.entity.sqlserver")
                .build();
    }

    @Bean
    public PlatformTransactionManager sqlserverTransactionManager(
            final @Qualifier("sqlserverEntityManagerFactory") LocalContainerEntityManagerFactoryBean sqlserverEntityManagerFactory) {
        return new JpaTransactionManager(sqlserverEntityManagerFactory.getObject());
    }
}
