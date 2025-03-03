package org.rexapi.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.rexapi.models.dinopet.DinoPet;
import org.rexapi.models.user.User;
import org.rexapi.repositories.DinoPetRepository;

@ApplicationScoped
public class DinoPetService {
    @Inject
    DinoPetRepository dinoPetRepository;

    public void createDinoPet(User user){
        DinoPet dinoPet = new DinoPet();
        dinoPet.user = user;
        dinoPet.food = 100;   // Valores iniciales por defecto
        dinoPet.sanity = 100;
        dinoPet.hp = 100;
        dinoPet.clean = 100;
        dinoPetRepository.persist(dinoPet);
    }
}
