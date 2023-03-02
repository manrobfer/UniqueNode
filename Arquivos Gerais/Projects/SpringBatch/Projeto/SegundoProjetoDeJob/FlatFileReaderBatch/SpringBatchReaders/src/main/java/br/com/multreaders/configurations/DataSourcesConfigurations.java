package br.com.multreaders.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourcesConfigurations {
    @Primary
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource SpringBatchPrimaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("business.datasource")
    public DataSource springBatchBusinessDataSource(){
        return DataSourceBuilder.create().build();
    }
}
