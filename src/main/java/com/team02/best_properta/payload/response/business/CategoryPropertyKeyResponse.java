package com.team02.best_properta.payload.response.business;

import com.team02.best_properta.entity.concretes.business.Category;
import com.team02.best_properta.entity.concretes.business.CategoryPropertyValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)

public class CategoryPropertyKeyResponse {

    private Long id;
    private String name;
    private Boolean builtIn;
    private Category category;
    private  String type;
    private List<CategoryPropertyValue> categoryPropertyValues;



}