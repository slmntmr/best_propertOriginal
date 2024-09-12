package com.team02.best_properta.service.business;
import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.entity.concretes.business.CategoryPropertyValue;
import com.team02.best_properta.payload.request.business.PropertyUpdateRequest;
import com.team02.best_properta.repository.business.CategoryPropertyValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryPropertyValueService {



    private final CategoryPropertyValueRepository categoryPropertyValueRepository;


    public List<CategoryPropertyValue> getPropertyValuesByAdvertId(Long advertId) {
        return categoryPropertyValueRepository.findByAdvertId(advertId);
    }
    @Transactional
    public void save(CategoryPropertyValue propertyValue) {
        categoryPropertyValueRepository.save(propertyValue);


    }
    @Transactional
    public void updateProperties(Long advertId, List<PropertyUpdateRequest> properties) {
        // Mevcut özellikleri siliyoruz
        categoryPropertyValueRepository.deleteByAdvertId(advertId);
        Advert advertIdForSave=new Advert();
        advertIdForSave.setId(advertId);
        // Yeni özellikleri ekliyoruz
        for (PropertyUpdateRequest property : properties) {
            CategoryPropertyValue propertyValue = new CategoryPropertyValue();
            propertyValue.setAdvert(advertIdForSave); // Veya setAdvert(advert) eğer ilişki var ise
            propertyValue.setCategoryPropertyKey(property.getKeyId());
            propertyValue.setValue(property.getValue());
            categoryPropertyValueRepository.save(propertyValue);
        }
    }
    public void deleteByAdvertId(Long id) {
        categoryPropertyValueRepository.deleteByAdvertId(id);
    }
}
