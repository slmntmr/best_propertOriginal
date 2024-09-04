package com.team02.best_properta.payload.response.user;


import com.team02.best_properta.payload.response.business.AdvertsGetIdResponse;
import com.team02.best_properta.payload.response.business.FavoritesGetIdResponse;
import com.team02.best_properta.payload.response.business.LogsGetIdResponse;
import com.team02.best_properta.payload.response.business.TourRequestsGetIdResponse;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class UserGetIdResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<AdvertsGetIdResponse> adverts;
    private List<TourRequestsGetIdResponse> tourRequests;
    private List<FavoritesGetIdResponse> favorites;
    private List<LogsGetIdResponse> logs;
}
