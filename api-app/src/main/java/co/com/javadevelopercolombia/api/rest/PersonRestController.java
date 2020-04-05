package co.com.javadevelopercolombia.api.rest;

import co.com.javadevelopercolombia.api.application.ApplicationPersonService;
import co.com.javadevelopercolombia.api.rest.assemblers.PersonAssembler;
import co.com.javadeveloperscolombia.api.dto.PersonRequestDto;
import co.com.javadeveloperscolombia.api.dto.PersonResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@Api(tags = "persons")
@RestController
public class PersonRestController {

    private final ApplicationPersonService applicationPersonService;
    private final PersonAssembler personAssembler;
    private final PagedResourcesAssembler<PersonResponseDto> pagedResourcesAssembler;

    public PersonRestController(ApplicationPersonService applicationPersonService, PersonAssembler personAssembler,
                                PagedResourcesAssembler<PersonResponseDto> pagedResourcesAssembler) {
        this.applicationPersonService = applicationPersonService;
        this.personAssembler = personAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @ApiOperation(value = "create a person", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @PostMapping(path = "/persons", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPerson(@Valid @RequestBody PersonRequestDto personRequestDto){

        EntityModel<PersonResponseDto> personResponseDtoEntityModel = personAssembler
                .toModel(applicationPersonService.create(personRequestDto));

        return status(CREATED).body(personResponseDtoEntityModel);
    }

    @ApiOperation(value = "get all persons",produces = APPLICATION_JSON_VALUE)
    @GetMapping(path = "/persons",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedModel<EntityModel<PersonResponseDto>>> getAll(@RequestParam(value="pageNumber", defaultValue="0") Integer pageNumber,
                                                                             @RequestParam(value="pageSize", defaultValue="10") Integer pageSize,
                                                                             @RequestParam(value="sortKey", defaultValue="id") String sortKey){

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortKey));
        Page<PersonResponseDto> entityModelList = this.applicationPersonService.getAll(pageable);

        PagedModel<EntityModel<PersonResponseDto>> personResponseDtoPagedModel =
                pagedResourcesAssembler.toModel(entityModelList, personAssembler);

        return ResponseEntity.ok(personResponseDtoPagedModel);

    }

    @GetMapping("/persons/{id}")
    public EntityModel<PersonResponseDto> searchById(@PathVariable Long id) {
        PersonResponseDto personResponseDto = applicationPersonService.searchById(id);
        return personAssembler.toModel(personResponseDto);
    }

}
