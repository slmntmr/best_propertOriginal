package com.team02.best_properta.service.helper;

import com.team02.best_properta.entity.concretes.business.CategoryPropertyKey;
import com.team02.best_properta.entity.concretes.business.CategoryPropertyValue;
import com.team02.best_properta.entity.concretes.business.Image;
import com.team02.best_properta.entity.concretes.business.TourRequest;

import com.team02.best_properta.payload.request.business.ImageDTO;
import com.team02.best_properta.payload.request.business.TourRequestDTO;
import com.team02.best_properta.service.business.CategoryPropertyKeyService;
import com.team02.best_properta.service.business.CategoryPropertyValueService;
import com.team02.best_properta.service.business.ImageService;

import com.team02.best_properta.service.business.TourRequestsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Component
/*@RequiredArgsConstructor*/
public class MethodHelper {

/*
    private final CategoryPropertyKeyService categoryPropertyKeyService;
    private final CategoryPropertyValueService categoryPropertyValueService;
    private final TourRequestsService tourRequestService;
    private final ImageService imageService;*/


    private CategoryPropertyKeyService categoryPropertyKeyService;
    private CategoryPropertyValueService categoryPropertyValueService;
    private TourRequestsService tourRequestService;
    private ImageService imageService;

    // Setter methods for dependency injection
    public void setCategoryPropertyKeyService(CategoryPropertyKeyService categoryPropertyKeyService) {
        this.categoryPropertyKeyService = categoryPropertyKeyService;
    }

    public void setCategoryPropertyValueService(CategoryPropertyValueService categoryPropertyValueService) {
        this.categoryPropertyValueService = categoryPropertyValueService;
    }

    public void setTourRequestService(TourRequestsService tourRequestService) {
        this.tourRequestService = tourRequestService;
    }

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }





    public int calculatePopularityScore(int viewCount, int tourRequestCount) {
        return 3 * tourRequestCount + viewCount;
    }
    public Map<String, String> getPropertiesForAdvert(Long advertId, Long categoryId) {
        List<CategoryPropertyKey> propertyKeys = categoryPropertyKeyService.getPropertyKeysByCategoryId(categoryId);
        List<CategoryPropertyValue> propertyValues = categoryPropertyValueService.getPropertyValuesByAdvertId(advertId);

        Map<Long, String> keyMap = new HashMap<>();
        for (CategoryPropertyKey key : propertyKeys) {
            keyMap.put(key.getId(), key.getName());
        }

        Map<String, String> properties = new HashMap<>();
        for (CategoryPropertyValue value : propertyValues) {
            String keyName = keyMap.get(value.getCategoryPropertyKey());
            if (keyName != null) {
                properties.put(keyName, value.getValue());
            }
        }

        return properties;
    }

    public List<TourRequestDTO> getTourRequestsForAdvert(Long advertId) {
        List<TourRequest> tourRequests = tourRequestService.getTourRequestsByAdvertId(advertId);
        return tourRequests.stream()
                .map(tr -> new TourRequestDTO(tr.getId(), tr.getTourDate(),tr.getTourTime(), tr.getStatus()))
                .collect(Collectors.toList());
    }

    public List<ImageDTO> getImagesForAdvert(Long advertId) {
        List<Image> images = imageService.getImagesByAdvertId(advertId);
        return images.stream()
                .map(img -> new ImageDTO(img.getId(), img.getName(), img.getType(), img.getFeatured()))
                .collect(Collectors.toList());
    }
    public String slugGenerate(String title) {
        // Title'ı küçük harfe çevir
        String normalizedTitle = Normalizer.normalize(title, Normalizer.Form.NFD);
        // Türkçe karakterleri ve özel karakterleri kaldır
        String slug = normalizedTitle.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .replaceAll("ğ", "g")
                .replaceAll("ü", "u")
                .replaceAll("ş", "s")
                .replaceAll("ı", "i")
                .replaceAll("ö", "o")
                .replaceAll("ç", "c");
        // Diğer tüm özel karakterleri ve boşlukları "-" ile değiştir
        slug = slug.toLowerCase(Locale.forLanguageTag("tr"))
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-+", "-")
                .replaceAll("^-|-$", ""); // Başa ve sona gelen "-" işaretlerini kaldır

        return slug;
    }
}