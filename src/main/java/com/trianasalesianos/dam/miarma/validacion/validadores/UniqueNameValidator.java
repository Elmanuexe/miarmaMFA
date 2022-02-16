package com.trianasalesianos.dam.miarma.validacion.validadores;

import com.trianasalesianos.dam.miarma.repository.UsuarioRepository;
import com.trianasalesianos.dam.miarma.validacion.anotaciones.UniqueName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void initialize(UniqueName constraintAnnotation) { }

    @Override
    public boolean isValid(String nick, ConstraintValidatorContext context) {
        return StringUtils.hasText(nick) && (!usuarioRepository.existsByNick(nick));
    }
}

