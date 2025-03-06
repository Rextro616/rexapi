package org.rexapi.services;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import org.rexapi.models.dinopet.DinoPet;
import org.rexapi.models.dinopet.DinoPetDTO;
import org.rexapi.models.season.Season;
import org.rexapi.models.user.User;
import org.rexapi.repositories.DinoPetRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DinoPetService {
    @Inject
    DinoPetRepository dinoPetRepository;

    @Inject
    SeasonService seasonService;

    @Transactional
    public void createDinoPet(User user) {
        DinoPet dinoPet = new DinoPet();
        dinoPet.setUser(user);
        dinoPet.setFood(100);   // Valores iniciales por defecto
        dinoPet.setHp(100);
        dinoPet.setSanity(100);
        dinoPet.setClean(100);
        dinoPetRepository.persist(dinoPet);
    }

    @Transactional
    @Scheduled(every = "5m")
    public void decreaseDinoPetsStatus() {
        Season currentSeason = seasonService.getSeasonByMonth();
        List<DinoPet> allDinoPets = dinoPetRepository.listAll();

        System.out.println("Ejecutando disminución de estados de DinoPets...");

        for (DinoPet dinoPet : allDinoPets) {
            dinoPet.setFood(Math.max(0, dinoPet.getFood() - currentSeason.foodDecrease));
            dinoPet.setSanity(Math.max(0, dinoPet.getSanity() - currentSeason.sanityDecrease));
            dinoPet.setClean(Math.max(0, dinoPet.getClean() - currentSeason.cleanDecrease));

            dinoPetRepository.persist(dinoPet);  // Persistimos cambios
        }
    }

    @Transactional
    public DinoPet increaseFood(Long id) {
        return increaseStat(id, "food");
    }

    @Transactional
    public DinoPet increaseClean(Long id) {
        return increaseStat(id, "clean");
    }

    @Transactional
    public DinoPet increaseSanity(Long id) {
        return increaseStat(id, "sanity");
    }

    private DinoPet increaseStat(Long id, String stat) {
        Season currentSeason = seasonService.getSeasonByMonth();
        DinoPet dinoPet = getDinoPetById(id);

        switch (stat) {
            case "food":
                dinoPet.setFood(Math.min(dinoPet.getFood() + currentSeason.foodIncrease, 100));
                break;
            case "clean":
                dinoPet.setClean(Math.min(dinoPet.getClean() + currentSeason.cleanIncrease, 100));
                break;
            case "sanity":
                dinoPet.setSanity(Math.min(dinoPet.getSanity() + currentSeason.sanityIncrease, 100));
                break;
            default:
                throw new IllegalArgumentException("Stat inválida: " + stat);
        }
        dinoPet.setHp(updateHP(dinoPet));
        dinoPetRepository.persist(dinoPet);
        return dinoPet;
    }

    private double updateHP(DinoPet dinoPet){
        return (dinoPet.getFood() * 0.4) + (dinoPet.getSanity() * 0.3) + (dinoPet.getClean() * 0.3);
    }

    public DinoPet getDinoPetById(Long id) {
        return Optional.ofNullable(dinoPetRepository.findById(id))
                .orElseThrow(() -> new WebApplicationException("DinoPet no encontrado", 404));
    }

    public DinoPetDTO transformDinoPetToDTO(Long id) {
        DinoPet dinoPet = getDinoPetById(id);
        return new DinoPetDTO(dinoPet.getHp(), dinoPet.getFood(), dinoPet.getSanity(), dinoPet.getClean());
    }

    @Scheduled(every = "5m") // Ejecutar cada 5 minutos
    @Transactional
    public void validateStats() {
        List<DinoPet> allDinoPets = dinoPetRepository.listAll();

        for (DinoPet dinoPet : allDinoPets) {
            if (dinoPet.getFood() < 50 || dinoPet.getSanity() < 50 || dinoPet.getClean() < 50 || dinoPet.getHp() < 50) {
                handleLowStats(dinoPet);
            }
        }
    }

    private void handleLowStats(DinoPet dinoPet) {

    }
}
