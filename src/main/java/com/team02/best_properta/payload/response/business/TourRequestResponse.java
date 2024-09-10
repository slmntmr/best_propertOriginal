package com.team02.best_properta.payload.response.business;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Builder
@Getter
@Setter
@AllArgsConstructor
public class TourRequestResponse {
    private Long id;
    private LocalDateTime tourDate;

}