package com.team02.best_properta.service.business;

import com.team02.best_properta.payload.mappers.CountryMapper;
import com.team02.best_properta.payload.response.business.CountryResponse;
import com.team02.best_properta.repository.business.CountryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public List<CountryResponse> getAllCountries() {
        return countryMapper.mapCountryListToCountryResponseList(countryRepository.findAll());
    }
}
