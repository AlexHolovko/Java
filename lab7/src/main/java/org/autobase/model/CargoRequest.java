package org.autobase.model;

import lombok.*;

@Data
@AllArgsConstructor
public class CargoRequest {
    private String destination;
    private int cargoWeightKg;
    private String cargoType;
    private int roadLengthKm;
}
