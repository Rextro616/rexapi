package org.rexapi.models.season;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Season {
    public final String name;
    public final int startMonth;
    public final int endMonth;
    public final int foodDecrease;
    public final int foodIncrease;
    public final int sanityDecrease;
    public final int sanityIncrease;
    public final int cleanDecrease;
    public final int cleanIncrease;

    private static final List<Season> SEASONS = List.of(
            new Season("Winter", 12, 2, 5, 5, 20, 10, 5, 10),
            new Season("Spring", 3, 5, 10, 20, 5, 10, 10, 10),
            new Season("Summer", 6, 8, 20, 10, 5, 10, 20, 10),
            new Season("Autumn", 9, 11, 10, 10, 10, 10, 10, 5)
    );

    public static Season getSeasonByMonth(int month) {
        return SEASONS.stream()
                .filter(season -> (month >= season.startMonth || month <= season.endMonth))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Mes inválido: " + month));
    }
}

