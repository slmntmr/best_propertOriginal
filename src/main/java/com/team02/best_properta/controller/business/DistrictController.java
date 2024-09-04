package com.team02.best_properta.controller.business;

import com.team02.best_properta.payload.response.business.DistrictResponse;
import com.team02.best_properta.service.business.DistrictService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/districts")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;
    @PreAuthorize("hasRole('ANONYMOUS')")
    @GetMapping
    public List<DistrictResponse> getDistricts() {
        return districtService.getAllDistricts();
    }
}