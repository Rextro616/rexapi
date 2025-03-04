package org.rexapi.services;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.rexapi.models.dinopet.DinoPet;
import org.rexapi.models.dinopet.DinoPetDTO;
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
        dinoPet.setUser(user);
        dinoPet.setFood(100);   // Valores iniciales por defecto
        dinoPet.setHp(100);
        dinoPet.setSanity(100);
        dinoPet.setClean(100);
        dinoPetRepository.persist(dinoPet);
    }

    @Transactional
    @Scheduled(every="5m")
    public void decreaseDinoPetsStatus() {
        List<DinoPet> allDinoPets = dinoPetRepository.listAll();
        Season currentSeason = seasonService.getSeasonByMonth();

        for (DinoPet dinoPet : allDinoPets) {
            dinoPet.setFood(Math.max(0, dinoPet.getFood() - currentSeason.foodDecrease));
            dinoPet.setSanity(Math.max(0, dinoPet.getSanity() - currentSeason.sanityDecrease));
            dinoPet.setClean(Math.max(0, dinoPet.getClean() - currentSeason.cleanDecrease));
        }
    }

    @Transactional
    public DinoPet increaseFood(Long id) {
        Season currentSeason = seasonService.getSeasonByMonth();
        DinoPet dinoPet = getDinoPetById(id);
        dinoPet.setFood(Math.min(dinoPet.getFood() + currentSeason.foodIncrease, 100));
        return dinoPet;
    }

    @Transactional
    public DinoPet increaseClean(Long id) {
        Season currentSeason = seasonService.getSeasonByMonth();
        DinoPet dinoPet = getDinoPetById(id);
        dinoPet.setClean(Math.min(dinoPet.getClean() + currentSeason.cleanIncrease, 100));
        return dinoPet;
    }

    @Transactional
    public DinoPet increaseSanity(Long id) {
        Season currentSeason = seasonService.getSeasonByMonth();
        DinoPet dinoPet = getDinoPetById(id);
        dinoPet.setSanity(Math.min(dinoPet.getSanity() + currentSeason.sanityIncrease, 100));
        return dinoPet;
    }

    @Transactional
    public DinoPet getDinoPetById(Long id) {
        return dinoPetRepository.findById(id);
    }

    @Transactional
    public DinoPetDTO transformDinoPetToDTO(Long id) {
        DinoPet dinoPet = getDinoPetById(id);
        return new DinoPetDTO(dinoPet.getHp(), dinoPet.getFood(), dinoPet.getSanity(), dinoPet.getClean());
    }
}
