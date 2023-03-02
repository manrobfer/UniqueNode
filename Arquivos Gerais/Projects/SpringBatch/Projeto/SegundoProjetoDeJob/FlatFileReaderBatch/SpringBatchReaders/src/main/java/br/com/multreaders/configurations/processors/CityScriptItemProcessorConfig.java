package br.com.multreaders.configurations.processors;

import br.com.multreaders.model.City;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ScriptItemProcessor;
import org.springframework.batch.item.support.builder.ScriptItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CityScriptItemProcessorConfig {

    @Bean
    public ItemProcessor<City, City>  scriptCityItemProcessor(){
       return new ScriptItemProcessorBuilder<City, City>()
                .language("nashorn")
                .scriptSource(
                        " var state = item.getState();" +
                        " var stateFileExists = `ls | grep ${state}.txt`; " +
                        " if (!stateFileExists) item; else null;"
                ).build();
    }

}
