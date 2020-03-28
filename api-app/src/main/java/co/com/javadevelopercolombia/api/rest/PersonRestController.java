package co.com.javadevelopercolombia.api.rest;

import co.com.javadevelopercolombia.api.application.ApplicationPersonService;
import co.com.javadeveloperscolombia.api.dto.PersonRequestDto;
import co.com.javadeveloperscolombia.api.dto.PersonResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@Api(tags = "persons")
@RestController
public class PersonRestController {

    private final ApplicationPersonService applicationPersonService;

    public PersonRestController(ApplicationPersonService applicationPersonService) {
        this.applicationPersonService = applicationPersonService;
    }

    @ApiOperation(value = "create a person", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @PostMapping(path = "/persons", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponseDto> createPerson(@Valid @RequestBody PersonRequestDto personRequestDto){
        return status(CREATED).body(applicationPersonService.create(personRequestDto));
    }

    @ApiOperation(value = "get all persons",produces = APPLICATION_JSON_VALUE)
    @GetMapping(path = "/persons",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonResponseDto>> getAll(){
        return ResponseEntity.ok(this.applicationPersonService.getAll());
    }

}
