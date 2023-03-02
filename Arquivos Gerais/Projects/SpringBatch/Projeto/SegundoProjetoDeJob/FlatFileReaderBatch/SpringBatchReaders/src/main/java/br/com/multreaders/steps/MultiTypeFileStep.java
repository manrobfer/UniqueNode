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
public class MultiTypeFileStep {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean(name = "multiTypesStep")
    public Step multiTypeFileReader( @Qualifier("multiTypeReader") ItemReader multiTypeReader,
                                     @Qualifier("multiFormatWriter") ItemWriter multiTypeWriter){
        return stepBuilderFactory
                .get("multiTypeStep")
                .chunk(3)
                .reader(multiTypeReader)
                .writer(multiTypeWriter)
                .build();
    }

}
