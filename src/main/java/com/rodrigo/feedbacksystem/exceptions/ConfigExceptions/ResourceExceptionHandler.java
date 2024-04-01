package com.rodrigo.feedbacksystem.exceptions.ConfigExceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class) // Excesao no qual vai ser capturada
    public ResponseEntity<StandardError> objNotFoundException(ObjectNotFoundException ex, HttpServletRequest request){

        StandardError error = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(), // resposta para a escessao
                "Objeto não encontrado", // mensagem para excessao
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)  // campos nulos
    public ResponseEntity<ValidationError> validationErrors(MethodArgumentNotValidException ex,
                                                            HttpServletRequest request){

        ValidationError errors = new ValidationError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                "Erro na validação dos campos",
                request.getRequestURI());

        for(FieldError x: ex.getBindingResult().getFieldErrors()){
            errors.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)  // Excesao no qual vai ser capturada
    public ResponseEntity<StandardError> dataIntegrityViolationException
            (DataIntegrityViolationException ex, HttpServletRequest request) {  // parametro do tipo DataIntegrityViolationException

        StandardError error= new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),  // resposta para a escessao
                "Violação de Dados", // mensagem para excessao
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error); // retorno para a excessao
    }
}
