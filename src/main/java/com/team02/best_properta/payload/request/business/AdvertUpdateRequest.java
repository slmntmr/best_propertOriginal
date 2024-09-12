package com.team02.best_properta.payload.request.business;

import com.team02.best_properta.entity.concretes.business.*;
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
public class AdvertUpdateRequest {
    private String title;
    private District districtId;
    private String description;
    private Category categoryId;
    private BigDecimal price;
    private Boolean isActive;
    private List<PropertyUpdateRequest> properties;
    private AdvertType advertTypeId;
    private Country countryId;
    private City cityId;
}
