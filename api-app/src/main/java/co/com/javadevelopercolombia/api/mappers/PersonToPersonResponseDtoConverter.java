package co.com.javadevelopercolombia.api.mappers;

import co.com.javadevelopercolombia.api.domain.entities.Person;
import co.com.javadeveloperscolombia.api.dto.PersonResponseDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

import java.text.SimpleDateFormat;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class PersonToPersonResponseDtoConverter extends CustomMapper<Person, PersonResponseDto> {

    @Override
    public void mapAtoB(Person person, PersonResponseDto personResponseDto, MappingContext context) {
        super.mapAtoB(person, personResponseDto, context);
        personResponseDto.setName(format("%s %s", person.getFirstName(),
                isBlank(person.getSecondName()) ? "" : person.getSecondName()));

        personResponseDto.setLastName(format("%s %s", person.getFirstSurname(),
                isBlank(person.getSecondSurname()) ? "" : person.getSecondSurname()));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        personResponseDto.setBirthday(formatter.format(person.getBirthday()));
    }
}
