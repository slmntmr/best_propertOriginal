package com.team02.best_properta.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.entity.concretes.business.CategoryPropertyKey;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponse {

    private Long id;
    private String title;
    private String icon;
    private Boolean builtIn;
    private Integer seq;
    private String slug;
    private Boolean isActive;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private List<Advert> adverts;
    private List<CategoryPropertyKey> categoryPropertyKeys;



}