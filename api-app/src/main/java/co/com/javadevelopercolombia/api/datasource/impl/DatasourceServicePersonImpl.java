package co.com.javadevelopercolombia.api.datasource.impl;

import co.com.javadevelopercolombia.api.datasource.DatasourceServicePerson;
import co.com.javadevelopercolombia.api.datasource.repositories.PersonRepository;
import co.com.javadevelopercolombia.api.domain.entities.Person;
import co.com.javadevelopercolombia.api.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatasourceServicePersonImpl implements DatasourceServicePerson {

    private final PersonRepository personRepository;

    public DatasourceServicePersonImpl(PersonRepository personRepository) {
       this.personRepository = personRepository;
    }

    @Override
    public Person create(Person person){
        return personRepository.save(person);
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("person with id not found"));
    }

    @Override
    public Page<Person> getAll(Pageable pageable){
        return personRepository.findAll(pageable);
    }

}
