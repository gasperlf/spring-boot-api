package co.com.javadeveloperscolombia.api.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonResponseDto {

    private Long id;
    private String dni;

    private String name;
    private String lastName;
    private String gender;

    private String birthday;
    private String address;
    private String telephoneNumber;
    private String cellphoneNumber;

}
