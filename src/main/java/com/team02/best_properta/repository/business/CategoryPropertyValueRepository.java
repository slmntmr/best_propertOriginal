package com.team02.best_properta.repository.business;

import com.team02.best_properta.entity.concretes.business.CategoryPropertyKey;
import com.team02.best_properta.entity.concretes.business.CategoryPropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryPropertyValueRepository extends JpaRepository<CategoryPropertyValue, Long> {



    List<CategoryPropertyValue> findByAdvertId(Long advertId);
    void deleteByAdvertId(Long advertId);
}