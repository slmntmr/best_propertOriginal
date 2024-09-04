package com.team02.best_properta.repository.business;


import com.team02.best_properta.entity.concretes.business.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}