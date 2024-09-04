package com.team02.best_properta.payload.response.business;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogsGetIdResponse {
    private Long id;
    private String log;
    private LocalDateTime createAt;

    // Getters and Setters
}
