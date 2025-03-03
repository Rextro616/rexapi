package org.rexapi.services;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.rexapi.models.dinopet.DinoPet;
import org.rexapi.models.season.Season;
import org.rexapi.models.user.User;
import org.rexapi.repositories.DinoPetRepository;

import java.util.List;

@ApplicationScoped
public class DinoPetService {
    @Inject
    DinoPetRepository dinoPetRepository;

    @Inject
    SeasonService seasonService;

    public void createDinoPet(User user){
        DinoPet dinoPet = new DinoPet();
        dinoPet.user = user;
        dinoPet.food = 100;   // Valores iniciales por defecto
        dinoPet.sanity = 100;
        dinoPet.hp = 100;
        dinoPet.clean = 100;
        dinoPetRepository.persist(dinoPet);
    }

    @Transactional
    @Scheduled(every="5m")
    public void decreaseDinoPetsStatus() {
        Season currentSeason = seasonService.getSeasonByMonth();
        List<DinoPet> allDinoPets = dinoPetRepository.listAll();

        for (DinoPet dinoPet : allDinoPets) {
            dinoPet.food = Math.max(0, dinoPet.food - currentSeason.foodDecrease);
            dinoPet.sanity = Math.max(0, dinoPet.sanity - currentSeason.sanityDecrease);
            dinoPet.clean = Math.max(0, dinoPet.clean - currentSeason.cleanDecrease);
        }
    }


}
