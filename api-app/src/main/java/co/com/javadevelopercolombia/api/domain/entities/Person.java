package co.com.javadevelopercolombia.api.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {

    @Id
    @SequenceGenerator(name ="person_id_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
    private Long id;

    @NotNull
    private String document;

    @NotNull
    @Column (name = "first_name")
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
