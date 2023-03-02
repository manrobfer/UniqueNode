package br.com.multreaders.readers;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class MultiFormatFileReaderConfig {

    @Bean(name = "multiTypeReader")
    public FlatFileItemReader multiFormatFileReader(LineMapper multiLineMapper){

        return new FlatFileItemReaderBuilder()
                .name("multiFormatFileReader")
                .resource(new FileSystemResource("/home/manoel/Documentos/jobs/Student.txt"))
                .lineMapper(multiLineMapper)
                .build();



    }
}
