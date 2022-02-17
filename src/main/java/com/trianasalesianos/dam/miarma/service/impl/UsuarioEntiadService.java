package com.trianasalesianos.dam.miarma.service.impl;

import com.trianasalesianos.dam.miarma.model.Usuario;
import com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto.CreateUsuarioDto;
import com.trianasalesianos.dam.miarma.repository.UsuarioEntiadRepository;
import com.trianasalesianos.dam.miarma.service.FileService;
import com.trianasalesianos.dam.miarma.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service("usuarioDetallesService")
@RequiredArgsConstructor
public class UsuarioEntiadService extends BaseService<Usuario, UUID, UsuarioEntiadRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final FileService fileService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email + "no encontrado"));
    }

    public Usuario save(CreateUsuarioDto newUsuario, MultipartFile file) throws IOException {
        if (newUsuario.getPassword().contentEquals(newUsuario.getPassword2())){

            String nombreArchivo=fileService.reescalarAndGuardar(file,124);
            String uri = fileService.getUri(nombreArchivo);
            newUsuario.setUri(uri);

            String ruta=fileService.saveFile(file);

            Usuario u = Usuario.builder()
                    .password(passwordEncoder.encode(newUsuario.getPassword()))
                    .nick(newUsuario.getNick())
                    .email(newUsuario.getEmail())
                    .direccion(newUsuario.getDireccion())
                    .telefono(newUsuario.getTelefono())
                    .privado(newUsuario.getPrivado())
                    .nacimiento(newUsuario.getNacimiento())
                    .createdAt(LocalDateTime.now())
                    .avatar(ruta)
                    //TODO agregar rol
                    .build();
            return save(u);
        } else {
            return null;
        }
    }


}
