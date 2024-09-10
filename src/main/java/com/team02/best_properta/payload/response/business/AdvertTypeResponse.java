package com.team02.best_properta.payload.response.business;

import com.team02.best_properta.entity.concretes.business.AdvertType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class AdvertTypeResponse {
    private Long id;
    private String title;

}