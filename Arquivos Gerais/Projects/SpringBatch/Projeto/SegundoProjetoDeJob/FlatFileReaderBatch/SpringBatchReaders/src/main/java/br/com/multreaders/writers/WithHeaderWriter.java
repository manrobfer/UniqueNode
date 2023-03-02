package br.com.multreaders.writers;

import br.com.multreaders.footer.HeaderFileFooter;
import br.com.multreaders.model.Vehicle;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.file.*;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;

@Configuration
public class WithHeaderWriter {

    private BigDecimal totalPrice ;

    @Bean("multiWriter")
    public MultiResourceItemWriter<Vehicle> multiFileWriter(FlatFileItemWriter<Vehicle> headerItemWriter){
        return new MultiResourceItemWriterBuilder<Vehicle>( )
                .name("multiFileWriter")
                .resource( new  FileSystemResource("/home/manoel/Documentos/job/"))
                .delegate(headerItemWriter)
                .resourceSuffixCreator(sufixo())
                //.itemCountLimitPerResource(1)
                .build();

    }

    private ResourceSuffixCreator sufixo() {
        return new ResourceSuffixCreator() {
            @Override
            public String getSuffix(int i) {
                return i + "_zeca.txt";
            }
        };
    }
    @Bean("headerFileWriter")
    public FlatFileItemWriter<Vehicle> headerItemWriter(
            HeaderFileFooter footerCall
    ){
        return new FlatFileItemWriterBuilder<Vehicle>()
                .name("headerItemWriter")
                .resource(new FileSystemResource( "/home/manoel/Documentos/jobs/headerVehicleOut.txt"))
                .lineAggregator(carsTypes())
                .headerCallback(header())
                .footerCallback(footerCall)
                .build();
    }
    private FlatFileHeaderCallback header() {
        return new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.append("--------Valor--------");
            };
        };
      }
    private LineAggregator<Vehicle> carsTypes() {
        return new LineAggregator<Vehicle>() {
            @Override
            public String aggregate(Vehicle vehicle) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(String.format("\t %s", vehicle.getType()));
                stringBuilder.append(String.format("\t\t %s", vehicle.getAxis()));
                stringBuilder.append(String.format("\t %s", vehicle.getPrice()));
                stringBuilder.append(String.format("\t %s", vehicle.getState()));
                return stringBuilder.toString();
            }
        };
    }
}
