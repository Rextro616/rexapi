package org.rexapi.models.dinopet;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.rexapi.models.user.User;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DinoPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "health_points")
    @PositiveOrZero
    private double hp;

    @NotNull
    @Column
    @PositiveOrZero
    private double food;

    @NotNull
    @Column
    @PositiveOrZero
    private double sanity;

    @NotNull
    @Column
    @PositiveOrZero
    private double clean;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

