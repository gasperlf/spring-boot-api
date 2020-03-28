package co.com.javadevelopercolombia.api.application;

import co.com.javadeveloperscolombia.api.dto.PersonRequestDto;
import co.com.javadeveloperscolombia.api.dto.PersonResponseDto;

import java.util.List;

public interface ApplicationPersonService {

    PersonResponseDto create(PersonRequestDto personRequestDto);

    List<PersonResponseDto> getAll();
}
