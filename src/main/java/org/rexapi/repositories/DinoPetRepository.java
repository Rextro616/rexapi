package org.rexapi.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.rexapi.models.dinopet.DinoPet;

@ApplicationScoped
public class DinoPetRepository implements PanacheRepository<DinoPet> {

}
