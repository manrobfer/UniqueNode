package br.com.multreaders.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class AllStepsCentralizerJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job ProjectUniqueJob( @Qualifier("fixedStep") Step fixedLengthStep,
                                 @Qualifier("delimitedStep") Step delimitedFile,
                                 @Qualifier("multiTypesStep") Step multiType,
                                 @Qualifier("jdbcCursorStep") Step jdbcCursorReader,
                                 @Qualifier("jdbcPagingReaderStep") Step jdbcPagingReader,
                                 @Qualifier("skipStep") Step skipStep,
                                 @Qualifier("headerWriterStep") Step headerWriterStep){
         return jobBuilderFactory
                 .get("ProjectUniqueJob")
                 .start(fixedLengthStep)
                 .next(delimitedFile)
                 .next(multiType)
                 .next(jdbcCursorReader)
                 .next(jdbcPagingReader)
                 .next(skipStep)
                 .next(headerWriterStep)
                 .incrementer(new RunIdIncrementer())
                 .build();
    }
}
