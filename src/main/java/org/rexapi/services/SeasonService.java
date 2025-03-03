package org.rexapi.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.rexapi.models.season.Season;

@ApplicationScoped
public class SeasonService {
    public Season getSeasonByMonth(int month) {
        return Season.getSeasonByMonth(month);
    }
}
