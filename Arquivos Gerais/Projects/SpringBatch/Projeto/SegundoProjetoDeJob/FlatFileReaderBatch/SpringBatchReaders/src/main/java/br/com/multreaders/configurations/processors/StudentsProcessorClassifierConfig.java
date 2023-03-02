package br.com.multreaders.configurations.processors;

import br.com.multreaders.model.Student;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;

public class StudentsProcessorClassifierConfig {

    public ItemProcessor studentProcessor(){
        return new ClassifierCompositeItemProcessorBuilder<>()
                .classifier(studnetClassfier())
                .build();
    }

    private Classifier studnetClassfier() {
        return new Classifier<Object, ItemProcessor>() {

            @Override
            public ItemProcessor classify(Object o) {
                if (o instanceof Student)
                    return new StudentProcessor();
                else
                    return new GradeProcessor();
            }
        };

    }
}
