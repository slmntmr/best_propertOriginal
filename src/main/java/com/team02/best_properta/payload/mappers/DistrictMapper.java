package com.team02.best_properta.payload.mappers;

import com.team02.best_properta.entity.concretes.business.District;
import com.team02.best_properta.payload.response.business.DistrictResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DistrictMapper {

    public DistrictResponse mapDistrictToDistrictResponse(District district) {
        return DistrictResponse.builder()
                .id(district.getId())
                .name(district.getName())
                .build();
    }

    public List<DistrictResponse> mapDistrictListToDistrictResponseList(List<District> districts) {
        return districts.stream()
                .map(this::mapDistrictToDistrictResponse)
                .collect(Collectors.toList());
    }
}