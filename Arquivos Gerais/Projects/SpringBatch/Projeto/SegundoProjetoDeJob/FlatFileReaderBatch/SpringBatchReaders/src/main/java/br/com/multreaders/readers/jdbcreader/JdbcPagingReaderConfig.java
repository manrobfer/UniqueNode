package br.com.multreaders.readers.jdbcreader;

import br.com.multreaders.model.City;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JdbcPagingReaderConfig {

    @Bean(name = "jdbcPagingItemReader")
    public JdbcPagingItemReader<City> jdbcPagingItemReader(@Qualifier("springBatchBusinessDataSource") DataSource dataSource, PagingQueryProvider queryProvider){
        return new JdbcPagingItemReaderBuilder<City>()
                .name("jdbcPagingItemReader")
                .dataSource(dataSource)
                .queryProvider(queryProvider)
                .pageSize(2)
                .rowMapper(new BeanPropertyRowMapper<City>(City.class))
                .build();

    }

    @Bean
    public SqlPagingQueryProviderFactoryBean queryProvider(
             @Qualifier("springBatchBusinessDataSource") DataSource dataSource){
             SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
             queryProvider.setDataSource(dataSource);
             queryProvider.setSelectClause("Select *");
             queryProvider.setFromClause("from City");
             queryProvider.setSortKey("name");

        return queryProvider;
    }
}
