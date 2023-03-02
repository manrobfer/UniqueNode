package br.com.multreaders.readers;

import br.com.multreaders.model.Person;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
public class FixedLengthFileReaderConfig {

    @StepScope
    @Bean( name = "fixedReader")
    public FlatFileItemReader<Person> personItemReader(@Value("#{jobParameters['personFile']}") Resource personFile){
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                //.resource(personFile)
                .resource(new FileSystemResource("/home/manoel/Documentos/jobs/Person.txt"))
                .fixedLength()
                .columns(new Range[] {new Range(1,10), new Range(11,12), new Range(14,35),new Range(36,52)})
                .names(new String[] { "name","age","email","phone"})
                .targetType(Person.class)
                .build();


    }
}
