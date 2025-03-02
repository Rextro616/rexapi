package org.rexapi.models.user;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User extends PanacheEntity {

    @NotBlank
    @Column(length = 80, nullable = false)
    public String username;

    @NotBlank
    @Column(unique = true, nullable = false)
    public String token;
}
