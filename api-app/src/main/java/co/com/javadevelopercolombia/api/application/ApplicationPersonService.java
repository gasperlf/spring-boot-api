package co.com.javadevelopercolombia.api.application;

import co.com.javadeveloperscolombia.api.dto.PersonRequestDto;
import co.com.javadeveloperscolombia.api.dto.PersonResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationPersonService {

    PersonResponseDto create(PersonRequestDto personRequestDto);

    PersonResponseDto searchById(Long personId);

    Page<PersonResponseDto> getAll(Pageable pageable);
}
