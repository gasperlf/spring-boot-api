package co.com.javadevelopercolombia.api.application.impl;

import co.com.javadevelopercolombia.api.application.ApplicationPersonService;
import co.com.javadevelopercolombia.api.datasource.DatasourceServicePerson;
import co.com.javadevelopercolombia.api.domain.entities.Person;
import co.com.javadeveloperscolombia.api.dto.PersonRequestDto;
import co.com.javadeveloperscolombia.api.dto.PersonResponseDto;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationPersonServiceImpl implements ApplicationPersonService {

    private final DatasourceServicePerson datasourceServicePerson;
    private final ConversionService conversionService;
    private final MapperFacade mapperFacade;

    public ApplicationPersonServiceImpl(DatasourceServicePerson datasourceServicePerson,
                                        ConversionService conversionService,
                                        @Qualifier("loadMapperFacade") MapperFacade mapperFacade) {
        this.datasourceServicePerson = datasourceServicePerson;
        this.conversionService = conversionService;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public PersonResponseDto create(PersonRequestDto personRequestDto) {

        Person person = this.mapperFacade.map(personRequestDto, Person.class);
        person  = datasourceServicePerson.create(person);
        return this.conversionService.convert(person, PersonResponseDto.class);
    }

    public List<PersonResponseDto> getAll(){
        return this.datasourceServicePerson
                .getAll()
                .stream()
                .map(person -> this.mapperFacade.map(person, PersonResponseDto.class))
                .collect(Collectors.toList());
    }
}
