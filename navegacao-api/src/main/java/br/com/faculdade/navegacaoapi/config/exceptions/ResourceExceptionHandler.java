package br.com.faculdade.navegacaoapi.config.exceptions;


import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.time.Instant;

@ControllerAdvice 
public class ResourceExceptionHandler {

   
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        
        String error = "Resource not found"; 
        HttpStatus status = HttpStatus.NOT_FOUND; 
        
        
        StandardError err = new StandardError(
            Instant.now(),          
            status.value(),         
            error,
            e.getMessage(),         
            request.getRequestURI() 
        );
        
        
        return ResponseEntity.status(status).body(err);
    }
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleGenericException(Exception e, HttpServletRequest request) {
        
        String error = "Internal server error";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; 
        
        String message = "Ocorreu um erro inesperado no servidor."; 
        
        StandardError err = new StandardError(
            Instant.now(),
            status.value(),
            error,
            message,
            request.getRequestURI()
        );
        
        e.printStackTrace(); 
        
        return ResponseEntity.status(status).body(err);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        
        String error = "Erro de validação";
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        
        
        String message = e.getBindingResult().getFieldError().getDefaultMessage();

        StandardError err = new StandardError(
            Instant.now(),
            status.value(),
            error,
            message,
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> databaseIntegrity(org.springframework.dao.DataIntegrityViolationException e, HttpServletRequest request) {
        
        String error = "Conflito de dados";
        HttpStatus status = HttpStatus.CONFLICT; // 409
        
        
        String message = "Violação de integridade. Um dado fornecido já existe no banco de dados.";

        StandardError err = new StandardError(
            Instant.now(),
            status.value(),
            error,
            message,
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    // Import necessário: org.springframework.security.authentication.BadCredentialsException
    @ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
    public ResponseEntity<StandardError> badCredentials(org.springframework.security.authentication.BadCredentialsException e, HttpServletRequest request) {
        
        HttpStatus status = HttpStatus.UNAUTHORIZED; 
        
        StandardError err = new StandardError(
            Instant.now(),
            status.value(),
            "Falha na Autenticação",
            "Dados de login inválidos (Email ou Senha incorretos)",
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }


}