package com.trianasalesianos.dam.miarma.errores.excepciones;

public class EmptyFileException extends RuntimeException{
    public EmptyFileException(Class clas){
        super (String.format("Fichero vacio: "+ clas.getName()));
    }
}
