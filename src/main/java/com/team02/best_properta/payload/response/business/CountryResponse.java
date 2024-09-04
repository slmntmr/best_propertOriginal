package com.team02.best_properta.payload.response.business;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryResponse {
    private Long id;
    private String name;
}