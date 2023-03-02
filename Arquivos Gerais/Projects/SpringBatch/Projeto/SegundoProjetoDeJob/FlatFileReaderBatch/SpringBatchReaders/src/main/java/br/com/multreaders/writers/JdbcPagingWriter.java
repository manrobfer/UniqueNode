package br.com.multreaders.writers;

import br.com.multreaders.model.City;
import br.com.multreaders.model.Person;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcPagingWriter {

    @Bean("jdbcPagingItemWriter")
    public ItemWriter<City> personItemWriter(){
        return items -> items.forEach(System.out::println);
    }

}
