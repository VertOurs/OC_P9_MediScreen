package fr.vertours.notems.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoteDoesNotExistException.class)
    public final ResponseEntity<String> handledPatientDoesNotExistException(NoteDoesNotExistException e) {
        log.error("Handling " + e.getClass().getSimpleName() + " due to " + e.getMessage() + ". More informations : "+e.getStackTrace());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
