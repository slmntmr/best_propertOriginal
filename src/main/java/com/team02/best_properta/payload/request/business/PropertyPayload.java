package com.team02.best_properta.payload.request.business;

import com.team02.best_properta.entity.concretes.business.CategoryPropertyKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyPayload {
    private CategoryPropertyKey keyId;
    private String value;
}
