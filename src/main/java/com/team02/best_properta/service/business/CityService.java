package com.team02.best_properta.service.business;


import com.team02.best_properta.payload.mappers.CityMapper;
import com.team02.best_properta.payload.response.business.CityResponse;
import com.team02.best_properta.repository.business.CityRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public List<CityResponse> getAllCities() {
        return cityMapper.mapCityListToCityResponseList(cityRepository.findAll());
    }
}