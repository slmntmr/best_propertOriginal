package com.team02.best_properta.payload.request.business;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertTypePayload {

    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title cannot be empty")
    String title;

    private boolean builtIn;
}