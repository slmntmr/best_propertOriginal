package com.team02.best_properta.payload.response.business;


import com.team02.best_properta.entity.concretes.business.AdvertType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class AdvertResponse {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String category;
    private AdvertType type;
    private int status;
    private LocalDateTime createdDate;

    public AdvertResponse(Long id, String title) {
    }

}