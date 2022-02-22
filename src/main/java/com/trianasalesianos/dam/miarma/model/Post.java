package com.trianasalesianos.dam.miarma.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String titulo, descripcion;

    private boolean privado;

    @ManyToOne
    private Usuario usuario;

    private String content;

    private String thumbnail;

    public void addToUsuario(Usuario u){
        this.usuario=u;
        u.getPublicaciones().add(this);
    }

    public void removeToUsuario(Usuario u){
        this.usuario=null;
        u.getPublicaciones().remove(this);
    }
}
