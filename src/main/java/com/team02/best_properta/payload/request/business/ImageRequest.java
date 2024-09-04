package com.team02.best_properta.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageRequest {
    private Long id;
    private Boolean featured;
    private String name;
    private String type;


}