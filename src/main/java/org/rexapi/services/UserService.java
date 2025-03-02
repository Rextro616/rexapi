package org.rexapi.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.rexapi.models.user.User;
import org.rexapi.models.user.UserDTO;
import org.rexapi.repositories.UserRepository;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    @Transactional
    public void crearUsuario(UserDTO dto) {
        User usuario = new User();
        usuario.username = dto.username;
        usuario.token = dto.token;
        userRepository.persist(usuario);
    }
}
