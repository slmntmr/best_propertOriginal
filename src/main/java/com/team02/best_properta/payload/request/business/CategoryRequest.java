package com.team02.best_properta.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CategoryRequest {

    private String title;
    private String icon;
    private Boolean builtIn;
    private Integer seq;
    private String slug;
    private Boolean isActive;
    private LocalDateTime createAt;
    private List<CategoryPropertyKeyRequest> categoryPropertyKeys;

    // İç içe sınıf (Property) - Kategori özellikleri
    public static class CategoryPropertyKeyRequest {
        public String getName;
        private String name;
        private String type;



    }



}