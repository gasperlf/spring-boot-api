package co.com.javadevelopercolombia.api.config;

import co.com.javadevelopercolombia.api.mappers.MapperPersonRequestDtoToPerson;
import co.com.javadevelopercolombia.api.mappers.MapperPersonToPersonResponseDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MapperConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new MapperPersonRequestDtoToPerson());
        registry.addConverter(new MapperPersonToPersonResponseDto());
    }
}
