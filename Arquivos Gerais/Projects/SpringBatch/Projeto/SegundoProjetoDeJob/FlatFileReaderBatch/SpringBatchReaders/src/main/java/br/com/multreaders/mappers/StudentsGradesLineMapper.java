package br.com.multreaders.mappers;

import br.com.multreaders.model.Grade;
import br.com.multreaders.model.Student;
import org.checkerframework.checker.signature.qual.CanonicalNameOrEmpty;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class StudentsGradesLineMapper {

    @Bean
    public PatternMatchingCompositeLineMapper compositeLineMapper(){
        PatternMatchingCompositeLineMapper pattern = new PatternMatchingCompositeLineMapper();
        pattern.setTokenizers(tokensFromLine());
        pattern.setFieldSetMappers(fieldsMapper());
        return pattern;
    }

    private Map<String, FieldSetMapper> fieldsMapper() {
        Map<String, FieldSetMapper> fieldsMappers = new HashMap<>();
        fieldsMappers.put("0*", fieldsToClassMapper(Student.class));
        fieldsMappers.put("1*", fieldsToClassMapper(Grade.class));
        return  fieldsMappers;
    }

    private FieldSetMapper fieldsToClassMapper(Class any) {
        BeanWrapperFieldSetMapper anyMapper = new BeanWrapperFieldSetMapper();
        anyMapper.setTargetType(any);
        return anyMapper;
    }

    private Map<String, LineTokenizer> tokensFromLine() {
        Map<String, LineTokenizer> tokenizerMap = new HashMap<>();
        tokenizerMap.put("0*", studentLineTokennizer());
        tokenizerMap.put("1*", gradesLineTokennizer());
        return tokenizerMap;
    }
    private LineTokenizer studentLineTokennizer() {
        DelimitedLineTokenizer lineTokenizer =  new DelimitedLineTokenizer();
        lineTokenizer.setNames("name","age","school","grade","email");
        lineTokenizer.setIncludedFields(1,2,3,4,5);
        return lineTokenizer;
    }
    private LineTokenizer  gradesLineTokennizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("subject", "grade", "date", "teacher");
        lineTokenizer.setIncludedFields(1, 2, 3, 4);
        return lineTokenizer;
    }


}
