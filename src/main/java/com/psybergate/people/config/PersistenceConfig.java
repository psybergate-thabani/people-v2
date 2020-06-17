package com.psybergate.people.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.sql.DataSource;

@Configuration
@EnableJpaAuditing
public class PersistenceConfig {
//    @Bean
//    public DataSource getDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("org.postgresql.Driver");
//        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/people");
//        dataSourceBuilder.username("psybergate");
//        dataSourceBuilder.password("password");
//        return dataSourceBuilder.build();
//    }
}
