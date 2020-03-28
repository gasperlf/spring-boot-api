package co.com.javadevelopercolombia.api.datasource;

import co.com.javadevelopercolombia.api.domain.entities.Person;

import java.util.List;

public interface DatasourceServicePerson {

    Person create(Person person);

    List<Person> getAll();
}
