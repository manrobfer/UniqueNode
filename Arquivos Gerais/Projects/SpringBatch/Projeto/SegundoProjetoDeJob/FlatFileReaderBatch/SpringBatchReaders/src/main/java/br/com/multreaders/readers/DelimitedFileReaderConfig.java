package br.com.multreaders.readers;

import br.com.multreaders.model.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class DelimitedFileReaderConfig {

    @Bean(name = "delimitedReader")
    public FlatFileItemReader<Person> delimitedFileReader(){
        return new FlatFileItemReaderBuilder<Person>()
                .name("delimitedFileReader")
                .resource(new FileSystemResource("/home/manoel/Documentos/jobs/Person2.txt"))
                .delimited()
                .names(new String[] { "name","age","email","phone"})
                .targetType(Person.class)
                .build();


    }
}
