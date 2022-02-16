package com.trianasalesianos.dam.miarma.errores.excepciones;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message) {
        super(message);
    }
}
