package triana.salesianos.dam.TrianaTourist.validacion.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import triana.salesianos.dam.TrianaTourist.repository.CategoryRepository;
import triana.salesianos.dam.TrianaTourist.repository.RouteRepository;
import triana.salesianos.dam.TrianaTourist.validacion.anotaciones.UniqueName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public void initialize(UniqueName constraintAnnotation) { }

    @Override
    public boolean isValid(String nombre, ConstraintValidatorContext context) {
        return StringUtils.hasText(nombre) && (!categoryRepository.existsByName(nombre) && !routeRepository.existsByName(nombre));
    }
}

