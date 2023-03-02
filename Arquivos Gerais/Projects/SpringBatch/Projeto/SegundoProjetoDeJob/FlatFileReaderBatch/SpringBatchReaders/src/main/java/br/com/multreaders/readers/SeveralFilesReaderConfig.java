package br.com.multreaders.readers;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;


public class SeveralFilesReaderConfig {


    public MultiResourceItemReader multiResourceItemReader(FlatFileItemReader multiFilesReder){
             return new MultiResourceItemReaderBuilder<>()
                     .name("multiResourceItemReader")
                     .resources()
                     .delegate((ResourceAwareItemReaderItemStream<?>) new StudentsGradesReaderConfig(multiFilesReder))
                     .build();
    }
}
