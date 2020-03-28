package co.com.javadevelopercolombia.api.mappers;

import co.com.javadevelopercolombia.api.domain.entities.Person;
import co.com.javadeveloperscolombia.api.dto.PersonRequestDto;
import org.springframework.core.convert.converter.Converter;

public class MapperPersonRequestDtoToPerson implements Converter<PersonRequestDto, Person> {

    @Override
    public Person convert(PersonRequestDto personRequestDto) {

        Person person = new Person();
        person.setDocument(personRequestDto.getDocument());
        person.setGender(personRequestDto.getGender());

        person.setFirstName(personRequestDto.getFirstName());
        person.setSecondName(personRequestDto.getSecondName());

        person.setFirstSurname(personRequestDto.getFirstSurname());
        person.setSecondSurname(personRequestDto.getSecondSurname());

        person.setBirthday(personRequestDto.getBirthday());
        person.setAddress(personRequestDto.getAddress());

        person.setCellphoneNumber(personRequestDto.getCellphoneNumber());
        person.setTelephoneNumber(personRequestDto.getTelephoneNumber());

        return person;
    }
}
