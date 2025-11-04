package es.notes.notes.exceptions;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//@ControllerAdvice pilla todas las excepciones de los controllers
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TareaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTareaNotFound(TareaNotFoundException e){
        //error 404
        HttpStatus status = HttpStatus.NOT_FOUND;

        //Se crea el objeto ErrorResponse
        ErrorResponse error = new ErrorResponse(status.value(), e.getMessage());

        //devuelve ResponseEntity
        return new ResponseEntity<>(error, status);
    }

    public ResponseEntity<ErrorResponse> handleDuplicateUsername(DuplicateUsernameException e) {
        //Error 404
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse error = new ErrorResponse(status.value(), e.getMessage());

        //devuelve ResponseEntity
        return new ResponseEntity<>(error, status);
    }
}
