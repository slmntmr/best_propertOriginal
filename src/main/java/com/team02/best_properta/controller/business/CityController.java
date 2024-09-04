package com.team02.best_properta.controller.business;

import com.team02.best_properta.payload.response.business.CityResponse;
import com.team02.best_properta.service.business.CityService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;
    @PreAuthorize("hasRole('ANONYMOUS')")
    @GetMapping
    public List<CityResponse> getCities() {
        return cityService.getAllCities();
    }
}