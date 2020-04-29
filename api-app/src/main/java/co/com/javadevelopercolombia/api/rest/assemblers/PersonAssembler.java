package co.com.javadevelopercolombia.api.rest.assemblers;

import co.com.javadevelopercolombia.api.rest.PersonRestController;
import co.com.javadeveloperscolombia.api.dto.PersonResponseDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PersonAssembler implements RepresentationModelAssembler<PersonResponseDto, EntityModel<PersonResponseDto>> {

    @Override
    public EntityModel<PersonResponseDto> toModel(PersonResponseDto entity) {

        return new EntityModel<>(entity,
                linkTo(methodOn(PersonRestController.class).searchById(entity.getId())).withSelfRel(),
                linkTo(methodOn(PersonRestController.class).getAll(0, 10, "id")).withRel("persons"));
    }
}
