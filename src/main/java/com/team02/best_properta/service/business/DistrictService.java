package com.team02.best_properta.service.business;


import com.team02.best_properta.payload.mappers.DistrictMapper;
import com.team02.best_properta.payload.response.business.DistrictResponse;
import com.team02.best_properta.repository.business.DistrictRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    public List<DistrictResponse> getAllDistricts() {
        return districtMapper.mapDistrictListToDistrictResponseList(districtRepository.findAll());
    }
}