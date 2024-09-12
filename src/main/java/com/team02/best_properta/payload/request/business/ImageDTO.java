package com.team02.best_properta.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private Long id;
    private String name;
    private String type;
    private boolean featured;
}
