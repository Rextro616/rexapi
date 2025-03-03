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

    @Inject
    DinoPetService dinoPetService;

    @Transactional
    public void createUser(UserDTO dto) {
        User user = new User();
        user.username = dto.username;
        user.token = dto.token;
        userRepository.persist(user);
        dinoPetService.createDinoPet(user);
    }
}
