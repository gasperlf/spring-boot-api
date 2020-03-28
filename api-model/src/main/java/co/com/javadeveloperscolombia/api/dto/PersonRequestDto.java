package co.com.javadeveloperscolombia.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class PersonRequestDto {

    @NotNull
    private String document;

    @NotNull
    private String firstName;

    private String secondName;
    @NotNull
    private String firstSurname;
    private String secondSurname;
    @NotNull
    private String gender;
    @NotNull
    private Date birthday;
    @NotNull
    private String address;

    private String telephoneNumber;

    private String cellphoneNumber;
}
