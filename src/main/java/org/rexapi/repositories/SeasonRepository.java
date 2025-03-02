package org.rexapi.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.rexapi.models.season.Season;

@ApplicationScoped
public class SeasonRepository implements PanacheRepository<Season>{
}
