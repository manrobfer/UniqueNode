package br.com.multreaders.readers;

import br.com.multreaders.model.Person;
import br.com.multreaders.model.Vehicle;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class HeaderReader {

    @Bean(name = "headerFileReader")
    public FlatFileItemReader<Vehicle> headerFileReader() {
        return new FlatFileItemReaderBuilder<Vehicle>()
                .name("delimitedHeaderFileReader")
                .resource(new FileSystemResource("/home/manoel/Documentos/jobs/vehicle.txt"))
                .delimited()
                .delimiter(",")
                .names(new String[]{"type", "axis", "price", "state"})
                .targetType(Vehicle.class)
                .build();
    };
}
