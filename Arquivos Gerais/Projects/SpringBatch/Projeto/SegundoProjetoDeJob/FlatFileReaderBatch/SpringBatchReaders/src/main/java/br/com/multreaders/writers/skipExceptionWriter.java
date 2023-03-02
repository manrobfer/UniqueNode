package br.com.multreaders.writers;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class skipExceptionWriter {

    @Bean(name = "skipWriter")
    public ItemWriter fromMultiFormatFileWriter(){
        return items -> items.forEach(System.out::println);
    }
}
