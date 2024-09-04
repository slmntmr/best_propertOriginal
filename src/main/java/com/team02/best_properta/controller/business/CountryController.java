package com.team02.best_properta.controller.business;

import com.team02.best_properta.payload.response.business.CountryResponse;
import com.team02.best_properta.service.business.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;
    @PreAuthorize("hasRole('ANONYMOUS')")
    @GetMapping
    public List<CountryResponse> getCountries() {
        return countryService.getAllCountries();
    }
}
