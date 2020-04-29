package co.com.javadevelopercolombia.api.datasource.repositories;

import co.com.javadevelopercolombia.api.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
