package co.com.javadevelopercolombia.api.rest.handlers;

import co.com.javadeveloperscolombia.api.handler.ProblemDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class HandlerGlobalError {


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handlerException(ConstraintViolationException e, HttpServletRequest httpServletRequest){

        log.info("{}", httpServletRequest.getRequestURI());

        StringBuilder builder = new StringBuilder();

        ProblemDetail problemDetail =  new ProblemDetail();
        problemDetail.setStatus(HttpStatus.BAD_REQUEST.toString());
        problemDetail.setResource(httpServletRequest.getRequestURI());

        e.getConstraintViolations()
                .forEach(error-> builder.append(
                        String.format("Value %s %s", error.getInvalidValue(), error.getMessage())
                ));
        problemDetail.setDetails(builder.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handlerException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest){

        log.info("{}", httpServletRequest.getRequestURI());

        StringBuilder builder = new StringBuilder();

        ProblemDetail problemDetail =  new ProblemDetail();
        problemDetail.setStatus(HttpStatus.BAD_REQUEST.toString());
        problemDetail.setResource(httpServletRequest.getRequestURI());

        problemDetail.setErrors(
                e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error-> new ProblemDetail("Invalid property",error.getDefaultMessage()))
                .collect(Collectors.toList()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> handlerException(HttpMessageNotReadableException e, HttpServletRequest httpServletRequest){

        log.info("{}", httpServletRequest.getRequestURI());

        StringBuilder builder = new StringBuilder();

        ProblemDetail problemDetail =  new ProblemDetail();
        problemDetail.setStatus(HttpStatus.BAD_REQUEST.toString());
        problemDetail.setResource(httpServletRequest.getRequestURI());
        problemDetail.setDetails("Invalid body " + e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }
}
