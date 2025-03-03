package org.rexapi.models.dinopet;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.rexapi.models.user.User;

@Entity
public class DinoPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Column(name = "health_points")
    @PositiveOrZero
    public double hp;

    @NotNull
    @Column
    @PositiveOrZero
    public double food;

    @NotNull
    @Column
    @PositiveOrZero
    public double sanity;

    @NotNull
    @Column
    @PositiveOrZero
    public double clean;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    public User user;
}

