package com.team02.best_properta.repository.business;

import com.team02.best_properta.entity.concretes.business.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findBySlug(String slug);
}