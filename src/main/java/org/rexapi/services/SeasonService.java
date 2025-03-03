package org.rexapi.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.rexapi.models.season.Season;

import java.time.LocalDate;

@ApplicationScoped
public class SeasonService {
    public Season getSeasonByMonth() {
        return Season.getSeasonByMonth(LocalDate.now().getMonthValue());
    }

}
