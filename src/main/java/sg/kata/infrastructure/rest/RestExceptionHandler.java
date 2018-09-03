package sg.kata.infrastructure.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {
    @ResponseStatus(value=HttpStatus.CONFLICT)
    @ExceptionHandler(RuntimeException.class)
    public String conflict(RuntimeException e) {
        return e.getMessage();
    }
}
