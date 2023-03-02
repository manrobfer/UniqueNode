package br.com.multreaders.configurations.processors;

import br.com.multreaders.model.Student;
import org.springframework.batch.item.ItemProcessor;

public class StudentProcessor implements ItemProcessor<Student, Student> {

    @Override
    public Student process(Student student) throws Exception {
        System.out.println(String.format("\n Setting rules to student %s ", student.getName()));
        return null;
    }
}
