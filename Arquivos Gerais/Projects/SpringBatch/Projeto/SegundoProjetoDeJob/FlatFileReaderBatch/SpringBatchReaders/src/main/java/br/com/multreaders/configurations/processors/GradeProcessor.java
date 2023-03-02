package br.com.multreaders.configurations.processors;

import br.com.multreaders.model.Grade;
import org.springframework.batch.item.ItemProcessor;

public class GradeProcessor implements ItemProcessor<Grade, Grade> {


    @Override
    public Grade process(Grade grade) throws Exception {
        System.out.println(String.format("\n Setting rules to grade %s ", grade.getGrade()));
        return null;
    }
}


