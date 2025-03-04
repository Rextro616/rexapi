package org.rexapi.models.dinopet;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DinoPetDTO {
    private double hp;
    private double food;
    private double sanity;
    private double clean;
}
