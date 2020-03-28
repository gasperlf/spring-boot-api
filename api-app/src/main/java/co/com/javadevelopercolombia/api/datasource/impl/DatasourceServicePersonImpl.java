package co.com.javadevelopercolombia.api.datasource.impl;

import co.com.javadevelopercolombia.api.datasource.DatasourceServicePerson;
import co.com.javadevelopercolombia.api.datasource.repositories.PersonRepository;
import co.com.javadevelopercolombia.api.domain.entities.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatasourceServicePersonImpl implements DatasourceServicePerson {

    private final PersonRepository personRepository;

    public DatasourceServicePersonImpl(PersonRepository personRepository) {
       this.personRepository = personRepository;
    }

    public Person create(Person person){
        return personRepository.save(person);
    }

    public List<Person> getAll(){
        return personRepository.findAll();
    }

}
