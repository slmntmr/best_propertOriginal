package com.team02.best_properta.payload.request.business;

import com.team02.best_properta.entity.concretes.business.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertRequest {

    @NotNull
    private String title;

    @NotNull
    private District district;

    @NotNull
    private String description;

    @NotNull
    private Category category;

    @NotNull
    private BigDecimal price;

    @NotNull
    private List<PropertyPayload> properties;

    @NotNull
    private AdvertType advertType;

    @NotNull
    private Country country;

    @NotNull
    private String location;

    @NotNull
    private City city;

    @NotNull
    private List<String> images;

}
