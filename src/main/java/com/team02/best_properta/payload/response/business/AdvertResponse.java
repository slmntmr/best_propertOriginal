package com.team02.best_properta.payload.response.business;


import com.team02.best_properta.entity.concretes.business.*;
import com.team02.best_properta.entity.concretes.user.Users;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class AdvertResponse {
    private Long id;
    private String title;
    private List<Image> images;
    private String description;
    private BigDecimal price;
    private int status;
    private LocalDateTime createAt;
    private int viewCount;
    private LocalDateTime updateAt;
    private String location;
    private AdvertType advertType;
    private Country country;
    private City city;
    private District district;
    private Users user;
    private Category category;

    public AdvertResponse(Long id, String title) {

    }

  /*  public AdvertResponse(Long id, String title) {
    }
*/
}