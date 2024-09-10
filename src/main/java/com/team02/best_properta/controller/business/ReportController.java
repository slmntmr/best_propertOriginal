package com.team02.best_properta.controller.business;

import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.entity.concretes.business.AdvertType;
import com.team02.best_properta.entity.concretes.user.Users;
import com.team02.best_properta.payload.response.business.AdvertResponse;
import com.team02.best_properta.payload.response.business.ReportResponse;
import com.team02.best_properta.payload.response.user.UserResponse;
import com.team02.best_properta.service.business.AdvertTypeService;
import com.team02.best_properta.service.business.AdvertsService;
import com.team02.best_properta.service.business.ReportService;
import com.team02.best_properta.service.user.UserService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/report")
public class ReportController {

    private final AdvertsService advertsService;
    private final AdvertTypeService advertTypeService;
    private final UserService userService;
    private final ReportService reportService;


    @GetMapping("/adverts")
    public ReportResponse getAdverts(@RequestParam(required = false) String date1,
                                     @RequestParam(required = false) String date2,
                                     @RequestParam(required = false) String category,
                                     @RequestParam(required = false) String type,
                                     @RequestParam(required = false) String status) {

        LocalDate startDate = date1 != null ? LocalDate.parse(date1) : null;
        LocalDate endDate = date2 != null ? LocalDate.parse(date2) : null;
        AdvertType advertType = advertTypeService.findByTitle(category);


        List<Advert> adverts = advertsService.findAdvertsByCriteria(startDate, endDate, category, advertType.getTitle(), status);

        ReportResponse response = new ReportResponse();
        response.setActivatedAdvert(Integer.parseInt(status));

        return reportService.getAdverts();
    }
    @GetMapping("adverts/most-popular-properties")
    public List<AdvertResponse> getMostPopularProperties(
            @RequestParam(value = "amount", required = true) int amount) {

        List<Advert> adverts = advertsService.getMostPopularProperties(amount);
        return adverts.stream()
                .map(advert -> new AdvertResponse(advert.getId(), advert.getTitle()))
                .toList();
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers(
            @RequestParam(value = "role", required = false) String role) {

        List<Users> users; // Doğru sınıf adı: User
        if (role != null) {
            users = userService.getUsersByRole(role);
        } else {
            users = userService.getAllUsers();
        }

        return users.stream()
                .map(user -> new UserResponse(user.getId(), user.getFirstName()))
                .toList();
    }



}