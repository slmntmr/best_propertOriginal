package com.team02.best_properta.payload.response.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageResponse {
    private Long id;
    private String name;
    private String type;
    private Boolean featured;
    private Long advertId; // İlanın ID'si
}