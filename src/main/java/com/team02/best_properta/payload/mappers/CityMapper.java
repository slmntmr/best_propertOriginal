package com.team02.best_properta.payload.mappers;


import com.team02.best_properta.entity.concretes.business.City;
import com.team02.best_properta.payload.response.business.CityResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityMapper {

    public CityResponse mapCityToCityResponse(City city) {
        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }

    public List<CityResponse> mapCityListToCityResponseList(List<City> cities) {
        return cities.stream()
                .map(this::mapCityToCityResponse)
                .collect(Collectors.toList());
    }
}