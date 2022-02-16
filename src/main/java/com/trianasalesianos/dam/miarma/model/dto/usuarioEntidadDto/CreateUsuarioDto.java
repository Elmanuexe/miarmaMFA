package com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto;

import com.trianasalesianos.dam.miarma.validacion.anotaciones.UniqueName;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateUsuarioDto {

    private String direccion, telefono, password, password2;

    @NotNull(message = "{usuario.privado.empty}")
    private Boolean privado;

    //@URL(message = "{usuairo.avatar.valid}")
    //@NotEmpty(message = "{usuario.avatar.empty}")
    private String avatar;

    @NotEmpty(message = "{usuario.email.empty}")
    private String email;

    @NotEmpty(message = "{usuario.nombre.empty}")
    @UniqueName(message = "{usuario.nombre.unico}")
    private String nick;

    @NotNull(message = "{usuario.nacimiento.empty}")
    private LocalDate nacimiento;
}
