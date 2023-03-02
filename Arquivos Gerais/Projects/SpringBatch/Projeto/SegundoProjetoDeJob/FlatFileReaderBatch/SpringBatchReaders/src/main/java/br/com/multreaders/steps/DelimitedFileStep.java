package br.com.multreaders.steps;

import br.com.multreaders.model.Person;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelimitedFileStep {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean(name = "delimitedStep")
    public Step delimitedReaderStep(@Qualifier("delimitedReader") ItemReader<Person> delimitedReader,
                                    @Qualifier("PersonFixedLengthValidator") ItemProcessor<Person, Person> personProcessor,
                                    @Qualifier("delimitedWriter") ItemWriter<Person> delimitedWriter){
        return stepBuilderFactory
                .get("delimitedReaderStep")
                .<Person, Person>chunk(2)
                .reader(delimitedReader)
                .processor(personProcessor)
                .writer(delimitedWriter)
                .build();
    }
}
