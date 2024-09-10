package com.team02.best_properta.payload.request.business;
import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.entity.concretes.user.Users;
import com.team02.best_properta.payload.response.business.TourRequestResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@Data
public class TourRequestAdmin {


    private Long id;
    private LocalDateTime tourDate;
    private Advert advert;
    private Users ownerUser;
    private Users guestUser;
}
