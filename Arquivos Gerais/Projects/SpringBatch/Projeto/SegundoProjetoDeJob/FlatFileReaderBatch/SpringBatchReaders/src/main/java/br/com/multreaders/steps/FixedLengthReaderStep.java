package br.com.multreaders.steps;

import br.com.multreaders.model.Person;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FixedLengthReaderStep {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

      @Bean(name = "fixedStep")
      public Step fixedLengthReaderStep( @Qualifier("fixedReader") ItemReader<Person> fixedItemReader,
                                         @Qualifier("fixedWriter") ItemWriter<Person> fixedItemWriter){
        return stepBuilderFactory
                .get("learnReaderStep")
                .<Person,Person>chunk(5)
                .reader(fixedItemReader)
                .writer(fixedItemWriter)
                .build();
    }
}
