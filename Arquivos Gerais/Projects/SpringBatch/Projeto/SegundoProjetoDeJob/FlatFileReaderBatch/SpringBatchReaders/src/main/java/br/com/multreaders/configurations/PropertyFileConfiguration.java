package br.com.multreaders.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropertyFileConfiguration {

    @Bean
    public PropertySourcesPlaceholderConfigurer configurer(){
        PropertySourcesPlaceholderConfigurer sourceFile = new PropertySourcesPlaceholderConfigurer();
        sourceFile.setLocation(new FileSystemResource("/etc/config/firsteverspringbatch/application.properties"));
        return sourceFile;
    }
}
