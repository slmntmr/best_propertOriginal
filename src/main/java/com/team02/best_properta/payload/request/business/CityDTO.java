package com.team02.best_properta.payload.request.business;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class CityDTO {
    private String city;
    private long amount;
}
