package br.com.multreaders.steps;

import br.com.multreaders.footer.HeaderFileFooter;
import br.com.multreaders.model.Vehicle;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeaderWriterStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean("headerWriterStep")
    public Step headerWriterStep(@Qualifier("headerFileReader") FlatFileItemReader<Vehicle> headerWriterReader,
                               //  @Qualifier("headerFileWriter") FlatFileItemWriter<Vehicle> headerWriterWriter,
                                @Qualifier("multiWriter") MultiResourceItemWriter headerWriterWriter,
                                HeaderFileFooter vehicles){
        return stepBuilderFactory
                .get("headerWriterStep")
                .<Vehicle, Vehicle>chunk(1)
                .reader(headerWriterReader)
                .writer(headerWriterWriter)
                .listener(vehicles)
                .build();
    }
}
