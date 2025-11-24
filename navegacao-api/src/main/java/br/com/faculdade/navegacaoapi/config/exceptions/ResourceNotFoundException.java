package br.com.faculdade.navegacaoapi.config.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
    
    public ResourceNotFoundException(String msg) {
        super(msg); 
    }
}