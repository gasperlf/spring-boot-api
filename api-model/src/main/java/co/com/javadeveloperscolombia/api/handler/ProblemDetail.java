package co.com.javadeveloperscolombia.api.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "problem detail response")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemDetail {

    private String status;

    private String resource;

    private String details;

    private List<ProblemDetail> errors;

    public ProblemDetail(String resource, String details){
        this.resource = resource;
        this.details = details;
    }
}
