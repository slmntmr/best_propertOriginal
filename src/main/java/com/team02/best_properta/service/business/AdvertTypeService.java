package com.team02.best_properta.service.business;

import com.team02.best_properta.entity.concretes.business.AdvertType;
import com.team02.best_properta.exception.ResourceNotFoundException;
import com.team02.best_properta.payload.mappers.AdvertTypeMapper;
import com.team02.best_properta.payload.request.business.AdvertTypePayload;
import com.team02.best_properta.payload.response.business.AdvertTypeResponse;
import com.team02.best_properta.repository.business.AdvertTypeRepository;
import com.team02.best_properta.repository.business.AdvertsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/*  @Autowired
    private AdvertTypeRepository advertTypeRepository;

    private AdvertTypePayload advertTypePayload;
    private  AdvertRepository advertRepository;
    private  AdvertService advertService;
*/

@Service
@AllArgsConstructor
public class AdvertTypeService {

    private final AdvertTypeRepository advertTypeRepository;
    private final AdvertsRepository advertsRepository;
    private final AdvertsService advertsService;




    public int countAdvertTypes() {
        return advertTypeRepository.countAdvertTypes();
    }

    public AdvertType findByTitle(String title) {
        return advertTypeRepository.findByTitle(title);
    }



    public List<AdvertTypeResponse> getAllAdvertTypes() {
        List<AdvertType> advertTypes = advertTypeRepository.findAll();
        return advertTypes.stream()
                .map(AdvertTypeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public AdvertType deleteAdvertType(Long id) throws ResourceNotFoundException {

        AdvertType advertType = advertTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advert type not found"));


        if (advertsRepository.existsByAdvertTypeId(id)) {
            throw new ResourceNotFoundException("Advert type cannot be deleted as it is related to adverts");
        }


        advertTypeRepository.delete(advertType);

        return advertType;
    }

    public AdvertType getAdvertTypeById(Long id) {
        Optional<AdvertType> advertType = advertTypeRepository.findById(id);
        return advertType.orElse(null);
    }

    public AdvertType createAdvertType(AdvertTypePayload payload) {
        if (payload.getTitle() == null || payload.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        AdvertType advertType = new AdvertType();
        advertType.setTitle(payload.getTitle());
        advertType.setBuiltIn(payload.isBuiltIn());


        return advertTypeRepository.save(advertType);
    }

    public AdvertType updateAdvertType(Long id, AdvertType advertTypeDetails) {
        Optional<AdvertType> optionalAdvertType = advertTypeRepository.findById(id);

        if (optionalAdvertType.isPresent()) {
            AdvertType advertType = optionalAdvertType.get();
            advertType.setTitle(advertTypeDetails.getTitle());
            return advertTypeRepository.save(advertType);
        } else {
            return null;
        }
    }










}