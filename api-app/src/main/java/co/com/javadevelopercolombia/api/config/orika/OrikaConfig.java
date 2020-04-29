package co.com.javadevelopercolombia.api.config.orika;

import co.com.javadevelopercolombia.api.domain.entities.Person;
import co.com.javadevelopercolombia.api.mappers.PersonRequestToPersonMapper;
import co.com.javadevelopercolombia.api.mappers.PersonToPersonResponseDtoConverter;
import co.com.javadeveloperscolombia.api.dto.PersonRequestDto;
import co.com.javadeveloperscolombia.api.dto.PersonResponseDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
public class OrikaConfig {

    @Autowired
    private MapperFactory mapperFactory;

    @Bean
    public MapperFactoryBean loadFactory() {
        return new MapperFactoryBean();
    }

    @Bean
    public MapperFacade loadMapperFacade(MapperFactory factory) {
        return factory.getMapperFacade();
    }

    @PostConstruct
    public void init() {
        //mapperFactory.getConverterFactory().registerConverter(new LocalDateTimeConverter());
        mapperFactory.classMap(PersonRequestDto.class, Person.class)
                .byDefault()
                .customize(new PersonRequestToPersonMapper())
                .register();
        mapperFactory.classMap(Person.class, PersonResponseDto.class)
                .byDefault()
                .customize(new PersonToPersonResponseDtoConverter())
                .register();
    }

}
