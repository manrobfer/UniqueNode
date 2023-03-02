package br.com.multreaders.readers;

import br.com.multreaders.model.Grade;
import br.com.multreaders.model.Student;
import org.springframework.batch.item.*;

public class StudentsGradesReaderConfig implements ItemStreamReader<Student> {
    private Object currentObject;
    private ItemStreamReader<Object> delegate;

    public StudentsGradesReaderConfig(ItemStreamReader<Object> delegate){
        this.delegate = delegate;
    }

    @Override
    public Student read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(currentObject == null)
            currentObject = delegate.read();

       Student student = (Student) currentObject;
        currentObject = null;

        if(student != null){
            while(peek() instanceof Grade)
                student.getGrades().add((Grade) currentObject);
          }
        return student;
    }

    private Object peek() throws Exception {
        currentObject = delegate.read();
                return currentObject;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
           delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
      delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
     delegate.close();
    }
}
