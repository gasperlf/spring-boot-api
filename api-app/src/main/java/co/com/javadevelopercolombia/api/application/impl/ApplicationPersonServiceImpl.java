package co.com.javadevelopercolombia.api.application.impl;

import co.com.javadevelopercolombia.api.application.ApplicationPersonService;
import co.com.javadevelopercolombia.api.datasource.DatasourceServicePerson;
import co.com.javadevelopercolombia.api.domain.entities.Person;
import co.com.javadeveloperscolombia.api.dto.PersonRequestDto;
import co.com.javadeveloperscolombia.api.dto.PersonResponseDto;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationPersonServiceImpl implements ApplicationPersonService {

    private final DatasourceServicePerson datasourceServicePerson;
    private final MapperFacade mapperFacade;

    public ApplicationPersonServiceImpl(DatasourceServicePerson datasourceServicePerson,
                                        @Qualifier("loadMapperFacade") MapperFacade mapperFacade) {
        this.datasourceServicePerson = datasourceServicePerson;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public PersonResponseDto create(PersonRequestDto personRequestDto) {
        Person person = this.mapperFacade.map(personRequestDto, Person.class);
        person = datasourceServicePerson.create(person);
        return this.mapperFacade.map(person, PersonResponseDto.class);
    }

    @Override
    public PersonResponseDto searchById(Long personId) {
        return this.mapperFacade.map(datasourceServicePerson.findById(personId), PersonResponseDto.class);
    }

    @Override
    public Page<PersonResponseDto> getAll(Pageable pageable) {
        Page<Person> personPage = this.datasourceServicePerson.getAll(pageable);
        List<PersonResponseDto> personResponseDtoList = personPage.getContent()
                .stream()
                .map(person -> this.mapperFacade.map(person, PersonResponseDto.class))
                .collect(Collectors.toList());
        return new PageImpl<>(personResponseDtoList, pageable, personPage.getTotalElements());

    }
}
