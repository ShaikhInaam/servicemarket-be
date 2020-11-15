package com.market.servicemarket.config;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "usageEntityManagerFactory",
        transactionManagerRef = "usageTransactionManager",
        basePackages = { "com.market.servicemarket.usage_analysis_repository" })
public class usageAnalysisDBConfig {

    @Bean(name = "usageDataSource")
    @ConfigurationProperties(prefix="usage.datasource")
    public DataSource barDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "usageEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean usageEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("usageDataSource") DataSource barDataSource) {
        return builder
                .dataSource(barDataSource)
                .packages("com.market.servicemarket.usage_analysis_entity")
                .persistenceUnit("usage")
                .build();
    }

    @Bean(name = "usageTransactionManager")
    public PlatformTransactionManager usageTransactionManager(
            @Qualifier("usageEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
        return new JpaTransactionManager(barEntityManagerFactory);
    }


}
