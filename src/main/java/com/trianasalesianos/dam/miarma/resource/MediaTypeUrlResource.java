package com.trianasalesianos.dam.miarma.resource;

import com.trianasalesianos.dam.miarma.errores.excepciones.NotSuportedContentException;
import org.apache.tika.Tika;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;

public class MediaTypeUrlResource extends UrlResource {

    public MediaTypeUrlResource(URI uri) throws MalformedURLException {
        super(uri);
    }

    public String getType() {
        Tika tika = new Tika();
        try {
            return tika.detect(this.getFile());
        } catch (IOException e) {
            throw new NotSuportedContentException("Tipo de archivo no permitido");
        }
    }
}
