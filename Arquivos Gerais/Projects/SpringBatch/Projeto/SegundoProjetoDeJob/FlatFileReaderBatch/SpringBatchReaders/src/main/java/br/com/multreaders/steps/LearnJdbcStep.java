package br.com.multreaders.steps;

import br.com.multreaders.model.Person;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LearnJdbcStep {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Bean(name = "jdbcCursorStep")
    public Step jdbcReaderStep(@Qualifier("jdbcReader") JdbcCursorItemReader<Person> delimitedReader,
                              @Qualifier("jdbcItemWriter") ItemWriter<Person> delimitedWriter){
        return stepBuilderFactory
                .get("jdbcReaderStep")
                .<Person, Person>chunk(2)
                .reader(delimitedReader)
                .writer(delimitedWriter)
                .build();
    }
}
