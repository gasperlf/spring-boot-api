package co.com.javadevelopercolombia.api.mappers;

import co.com.javadevelopercolombia.api.domain.entities.Person;
import co.com.javadeveloperscolombia.api.dto.PersonResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class MapperPersonToPersonResponseDto implements Converter<Person, PersonResponseDto>  {

    @Override
    public PersonResponseDto convert(Person person) {

        PersonResponseDto personResponseDto = new PersonResponseDto();
        personResponseDto.setId(person.getId());
        personResponseDto.setName(person.getFirstName() + " " + (isNotBlank(person.getSecondName())? person.getSecondName() : ""));
        personResponseDto.setLastName(person.getFirstSurname() + " " + (isNotBlank(person.getSecondSurname())? person.getSecondSurname() : ""));
        personResponseDto.setDni(person.getDocument());
        personResponseDto.setBirthday(person.getBirthday().toString());
        personResponseDto.setGender(person.getGender());
        personResponseDto.setAddress(person.getAddress());
        return personResponseDto;
    }
}
