package br.com.multreaders.readers.jdbcreader;

import br.com.multreaders.model.Person;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JdbcCursorReaderConfig {

    @Bean("jdbcReader")
    public JdbcCursorItemReader<Person> JdbcCursorReader(@Qualifier("springBatchBusinessDataSource") DataSource dataSource){
        return new JdbcCursorItemReaderBuilder()
                .name("JdbcCursorReader")
                .dataSource(dataSource)
                .sql("Select * from Person")
                .rowMapper(new BeanPropertyRowMapper<Person>(Person.class))
                .build();

    }
}
