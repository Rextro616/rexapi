package org.rexapi.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.rexapi.models.user.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
