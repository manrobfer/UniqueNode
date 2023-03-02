package br.com.multreaders.writers;

import br.com.multreaders.model.Person;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

//ew Range[] {new Range(1,10), new Range(11,12), new Range(14,35),new Range(36,52)}
@Configuration
public class FixedtemWriter {

    @Bean("fixedWriter")
    public FlatFileItemWriter<Person> personItemWriter(){

        return new FlatFileItemWriterBuilder<Person>()
                .name("personItemWriter")
                .resource(new FileSystemResource("/home/manoel/Documentos/jobs/fixedPersonOut.txt"))
                .formatted()
                .format("%-9s %-1s %-20s %-17s")
                .names("name", "age", "email","phone")
                .build();
    }
}
