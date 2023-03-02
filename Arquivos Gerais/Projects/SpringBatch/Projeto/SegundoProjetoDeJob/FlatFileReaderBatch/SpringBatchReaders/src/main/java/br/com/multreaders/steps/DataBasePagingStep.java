package br.com.multreaders.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBasePagingStep {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean(name = "jdbcPagingReaderStep")
    public Step jdbcPagingStep( @Qualifier("jdbcPagingItemReader") JdbcPagingItemReader databasePagingReader,
                                @Qualifier("jdbcPagingItemWriter")  ItemWriter fromPagingDatabaseToFileWriter){
           return stepBuilderFactory
                   .get("stepBuilderFactory")
                   .chunk(3)
                   .reader(databasePagingReader)
                   .writer(fromPagingDatabaseToFileWriter)
                   .build();
    }

}
