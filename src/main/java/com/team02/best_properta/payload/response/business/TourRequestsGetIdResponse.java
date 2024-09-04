package com.team02.best_properta.payload.response.business;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourRequestsGetIdResponse {
    private Long id;
    private Integer status; // String yerine Integer olarak tanımlandı
    private LocalDateTime tourDate;



}
