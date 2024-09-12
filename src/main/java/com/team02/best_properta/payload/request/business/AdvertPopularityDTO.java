package com.team02.best_properta.payload.request.business;

import com.team02.best_properta.entity.concretes.business.Advert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class AdvertPopularityDTO {
    private final Advert advert;
    private final int popularityScore;
}
