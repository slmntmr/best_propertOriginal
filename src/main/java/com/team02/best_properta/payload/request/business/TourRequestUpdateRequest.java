package com.team02.best_properta.payload.request.business;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TourRequestUpdateRequest {

    private LocalDateTime tourDate;
    private LocalDateTime tourTime;
    private Long advertId;
}