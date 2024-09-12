package com.team02.best_properta.payload.request.business;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertDTO {
    private Long id;
    private String title;
    private String slug;
    private Map<String, String> properties;
    private List<ImageDTO> images; // Assuming ImageDTO is defined
    private List<TourRequestDTO> tourRequests; // Assuming TourRequestDTO is defined

}
