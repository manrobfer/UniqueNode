package br.com.multreaders.configurations.processors;



import br.com.multreaders.model.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;


@Configuration
public class PersonValidator {

    private Set<Integer> ages =  new HashSet<>();

    @Bean(name = "PersonFixedLengthValidator")
    public ItemProcessor<Person, Person> personItemProcessor(){
       // BeanValidatingItemProcessor<Person> processor = new BeanValidatingItemProcessor<Person>();
       // processor.setFilter(true);

        ValidatingItemProcessor<Person> processor = new ValidatingItemProcessor<Person>();
        processor.setValidator(personValidator());
        processor.setFilter(true);
        return processor;
    }

    private Validator<Person> personValidator() {
        return new Validator<Person>() {

            @Override
            public void validate(Person person) throws ValidationException {
                if (ages.contains(person.getAge()))
                    throw new ValidationException(String.format("This age ( %d ) has already been processed! ", person.getAge()));

                ages.add(person.getAge());
            }
        };
    }
}
