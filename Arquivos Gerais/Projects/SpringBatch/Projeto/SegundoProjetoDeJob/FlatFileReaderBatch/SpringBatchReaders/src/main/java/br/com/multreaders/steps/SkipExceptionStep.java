package br.com.multreaders.steps;

import br.com.multreaders.model.City;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SkipExceptionStep {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

   @Bean("skipStep")
    public Step skipException(@Qualifier("skipReader") ItemReader<City> skipExcetpionReader, @Qualifier("skipWriter") ItemWriter<City> skipExceptionWriter){

        return stepBuilderFactory
                .get("skipException")
                .<City, City>chunk(12)
                .reader(skipExcetpionReader)
                .writer(skipExceptionWriter)

                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(2)

                .build();

    }
}
