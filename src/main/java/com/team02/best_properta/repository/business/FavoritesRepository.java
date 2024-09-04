package com.team02.best_properta.repository.business;


import com.team02.best_properta.entity.concretes.business.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<Favorite, Long> {
    void deleteByUserId(Long userId);
}
