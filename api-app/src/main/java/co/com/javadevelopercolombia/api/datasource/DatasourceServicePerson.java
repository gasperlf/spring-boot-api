package co.com.javadevelopercolombia.api.datasource;

import co.com.javadevelopercolombia.api.domain.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DatasourceServicePerson {

    Person create(Person person);

    Person findById(Long id);

    Page<Person> getAll(Pageable pageable);
}
