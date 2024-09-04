package com.team02.best_properta.payload.mappers;



import com.team02.best_properta.entity.concretes.business.Country;
import com.team02.best_properta.payload.response.business.CountryResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryMapper {

    public CountryResponse mapCountryToCountryResponse(Country country) {
        return CountryResponse.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }

    public List<CountryResponse> mapCountryListToCountryResponseList(List<Country> countries) {
        return countries.stream()
                .map(this::mapCountryToCountryResponse)
                .collect(Collectors.toList());
    }
}