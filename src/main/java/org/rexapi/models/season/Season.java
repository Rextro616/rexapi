package org.rexapi.models.season;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Season extends PanacheEntity {

    @NotNull
    @Column(name = "start_month")
    public int startMonth;

    @NotNull
    @Column(name = "end_month")
    public int endMonth;

    @NotNull
    @Column(length = 10)
    public String param;

    @NotNull
    @Column(name = "decrease_value")
    public int decreaseValue;

    @NotNull
    @Column(name = "increase_value")
    public int increaseValue;
}

