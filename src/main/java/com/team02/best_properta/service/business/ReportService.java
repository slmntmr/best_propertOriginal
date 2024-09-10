package com.team02.best_properta.service.business;

import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.payload.response.business.ReportResponse;

import com.team02.best_properta.service.user.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ReportService {

    private final CategoryService categoryService;
    private final AdvertsService advertsService;
    private final AdvertTypeService advertTypeService;
    private final TourRequestsService tourRequestsService;
    private final UserService userService;
    private final EntityManager entityManager;


    public ReportResponse getAdverts() {
        ReportResponse response = new ReportResponse();
        response.setCategories(categoryService.countActiveCategories());
        response.setActivatedAdvert(advertsService.countActivatedAdverts());
        response.setAdvertTypes(advertTypeService.countAdvertTypes());
        response.setTourRequests(tourRequestsService.countTourRequests());
        response.setUsers(userService.countUsers());

        return response;
    }




    @Transactional
    public List<Advert> findAdvertsByCriteria(LocalDate date1, LocalDate date2, String category, String type, String status) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Advert> cq = cb.createQuery(Advert.class);
        Root<Advert> advert = cq.from(Advert.class);

        List<Predicate> predicates = new ArrayList<>();

        // Tarihleri kontrol et ve predicate ekle
        if (date1 != null && date2 != null && date1.isAfter(date2)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }

        if (date1 != null) {
            predicates.add(cb.greaterThanOrEqualTo(advert.get("date"), date1));
        }

        if (date2 != null) {
            predicates.add(cb.lessThanOrEqualTo(advert.get("date"), date2));
        }

        // Kategori, tür ve durum için validasyon yap
        if (category != null && !category.isEmpty()) {
            predicates.add(cb.equal(advert.get("category"), category));
        }

        if (type != null && !type.isEmpty()) {
            predicates.add(cb.equal(advert.get("type"), type));
        }

        if (status != null && !status.isEmpty()) {
            predicates.add(cb.equal(advert.get("status"), status));
        }

        // Predicates'i ekle ve sorguyu çalıştır
        cq.where(predicates.toArray(new Predicate[0]));

        try {
            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
            // Hata yönetimi
            throw new RuntimeException("Error while fetching adverts by criteria", e);
        }
    }
}