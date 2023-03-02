package br.com.multreaders.writers;

import br.com.multreaders.model.Person;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class DelimitedFileWriter {

    @Bean(name = "delimitedWriter")
    public FlatFileItemWriter<Person> delimitedWriter(){
        return new FlatFileItemWriterBuilder<Person>()
                .name("delimitedWriter")
                .resource(new FileSystemResource( "/home/manoel/Documentos/jobs/delimitedPersonOut.txt"))
                .delimited()
                .delimiter(";")
                .names("name", "age", "email","phone")
                .build();

    }
}
