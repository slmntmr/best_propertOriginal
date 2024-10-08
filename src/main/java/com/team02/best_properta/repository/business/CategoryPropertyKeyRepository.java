package com.team02.best_properta.repository.business;

import com.team02.best_properta.entity.concretes.business.CategoryPropertyKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryPropertyKeyRepository extends JpaRepository<CategoryPropertyKey, Long> {


    List<CategoryPropertyKey> findByCategoryId(Long categoryId);

}