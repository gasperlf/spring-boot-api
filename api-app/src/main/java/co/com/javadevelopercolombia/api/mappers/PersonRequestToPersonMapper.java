package co.com.javadevelopercolombia.api.mappers;

import co.com.javadevelopercolombia.api.domain.entities.Person;
import co.com.javadeveloperscolombia.api.dto.PersonRequestDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class PersonRequestToPersonMapper extends CustomMapper<PersonRequestDto, Person> {

    @Override
    public void mapAtoB(PersonRequestDto personRequestDto, Person person, MappingContext context) {
        super.mapAtoB(personRequestDto, person, context);
    }
}
